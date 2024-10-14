import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("search")
    suspend fun searchLocation(
        @Query("q") query: String,
        @Query("format") format: String = "json",
        @Query("viewbox") viewBox: String? = null,
        @Query("bounded") bounded: Int? = null
    ): List<LocationResult>
}

data class LocationResult(
        val lat: String,
        val lon: String,
        val display_name: String
)

