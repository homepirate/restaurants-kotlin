import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.restaurants.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var currentZoomLevel = 18.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById(R.id.mapView)

        Configuration.getInstance().load(
            requireContext(),
            requireActivity().getPreferences(Context.MODE_PRIVATE)
        )

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mapView.setMultiTouchControls(true)
        setupSearchBar(view)
        checkLocationPermission()

        return view
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
            return
        }


        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val userLocation = GeoPoint(location.latitude, location.longitude)
                setMapLocation(userLocation, "You are here")
            } else {
                setDefaultLocation()
            }
        }
    }

    private fun setDefaultLocation() {
        val defaultLocation = GeoPoint(55.7558, 37.6173)
        setMapLocation(defaultLocation, "Default Location", 10.0)
    }

    private fun setMapLocation(
        geoPoint: GeoPoint,
        title: String,
        zoomLevel: Double = currentZoomLevel
    ) {
        mapView.controller.setZoom(zoomLevel)
        mapView.controller.setCenter(geoPoint)
        val marker = Marker(mapView)
        marker.position = geoPoint
        marker.title = title
        mapView.overlays.add(marker)
    }
    private fun setupSearchBar(view: View) {
        val searchBar: EditText = view.findViewById(R.id.searchBar)
        searchBar.setOnEditorActionListener { _, _, _ ->
            val query = searchBar.text.toString()
            if (query.isNotBlank()) {
                searchForLocation(query)
            }
            true
        }
    }
    private fun searchForLocation(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nominatim.openstreetmap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GeocodingService::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                // First, get the user's current location
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED) {

                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        if (location != null) {
                            val userLocation = GeoPoint(location.latitude, location.longitude)

                            // Define a bounding box for the search (restricting results to nearby locations)
                            val latDelta = 0.1
                            val lonDelta = 0.1
                            val minLat = userLocation.latitude - latDelta
                            val maxLat = userLocation.latitude + latDelta
                            val minLon = userLocation.longitude - lonDelta
                            val maxLon = userLocation.longitude + lonDelta
                            val viewBox = "$minLon,$minLat,$maxLon,$maxLat"

                            // Make the search request with bounding box
                            GlobalScope.launch(Dispatchers.Main) {
                                val results = service.searchLocation(query, viewBox = viewBox, bounded = 1)
                                if (results.isNotEmpty()) {
                                    // Find the nearest location by calculating distances
                                    var nearestResult: LocationResult? = null
                                    var shortestDistance = Double.MAX_VALUE

                                    for (result in results) {
                                        val resultGeoPoint = GeoPoint(result.lat.toDouble(), result.lon.toDouble())
                                        val distance = userLocation.distanceToAsDouble(resultGeoPoint)

                                        // Log each result and its distance
                                        println("Result: ${result.display_name} at (${result.lat}, ${result.lon}) - Distance: $distance meters")

                                        if (distance < shortestDistance) {
                                            shortestDistance = distance
                                            nearestResult = result
                                        }
                                    }

                                    // Display the nearest result
                                    nearestResult?.let {
                                        val nearestGeoPoint = GeoPoint(it.lat.toDouble(), it.lon.toDouble())
                                        setMapLocation(nearestGeoPoint, it.display_name)
                                    } ?: run {
                                        Toast.makeText(context, "No nearby locations found", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Unable to get current location", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_LOCATION_PERMISSION
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }






    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDetach()
    }


    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}
