import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.restaurants.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var currentZoomLevel = 18.0
    private val MIN_ZOOM_LEVEL = 10.0
    private val MAX_ZOOM_LEVEL = 20.0

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
