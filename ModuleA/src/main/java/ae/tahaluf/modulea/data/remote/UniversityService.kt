package ae.tahaluf.modulea.data.remote

import ae.tahaluf.modulea.domain.model.University
import retrofit2.Call
import retrofit2.http.GET

interface UniversityService {
    @GET("search?country=United%20Arab%20Emirates")
    fun getUniversities(): Call<List<University>>
}