package com.wolfcodea.randombleps.data.datasource

import com.wolfcodea.randombleps.core.utils.Constants
import com.wolfcodea.randombleps.core.endpoints.Endpoints
import com.wolfcodea.randombleps.core.exceptions.DataOrException
import com.wolfcodea.randombleps.data.models.AnimalsRandom
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface IAnimalsRandomApi {
    @Headers(
        "X-RapidAPI-Key: ${Constants.API_KEY}",
        "X-RapidAPI-Host: ${Constants.API_HOST}"
    )
    @GET("${Endpoints.RANDOM_MLEM}?tag={animalName}")
    suspend fun getAnimalsRandom(@Query("animalName") animalName: String): AnimalsRandom
}

class AnimalsRandomRemoteDatasource @Inject constructor(private val api: IAnimalsRandomApi) {
    private val dataOrException = DataOrException<AnimalsRandom, Boolean, Exception>()

    suspend fun getAnimalsRandom(animalName: String): DataOrException<AnimalsRandom, Boolean, java.lang.Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAnimalsRandom(animalName)
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}

