package ae.tahaluf.modulea.data.repository

import ae.tahaluf.modulea.data.local.UniversityDao
import ae.tahaluf.modulea.data.local.UniversityEntity
import ae.tahaluf.modulea.data.remote.UniversityService
import ae.tahaluf.modulea.domain.model.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UniversityRepository @Inject constructor(
    private val universityService: UniversityService,
    private val universityDao: UniversityDao
) {
    suspend fun fetchUniversities(): List<University> {
        return withContext(Dispatchers.IO) {
            try {
                val response = universityService.getUniversities().execute()
                if (response.isSuccessful) {
                    val universities = response.body() ?: emptyList()
                    // Cache the data in Room asynchronously
                    universityDao.insertAll(
                        universities.map {
                            UniversityEntity(
                                name = it.name,
                                country = it.country,
                                stateProvince = it.stateProvince,
                                domains = it.domains,
                                webPages = it.webPages,
                                alphaTwoCode = it.alphaTwoCode,
                            )
                        }
                    )
                    universities
                } else {
                    // If API fails, retrieve from local cache
                    universityDao.getAll().map {
                        University(
                            name = it.name,
                            country = it.country,
                            stateProvince = it.stateProvince,
                            domains = it.domains,
                            webPages = it.webPages,
                            alphaTwoCode = it.alphaTwoCode,
                        )
                    }
                }
            } catch (e: Exception) {
                // Handle network failure and fetch from cache
                universityDao.getAll().map {
                    University(
                        name = it.name,
                        country = it.country,
                        stateProvince = it.stateProvince,
                        domains = it.domains,
                        webPages = it.webPages,
                        alphaTwoCode = it.alphaTwoCode,
                    )
                }
            }
        }
    }

}