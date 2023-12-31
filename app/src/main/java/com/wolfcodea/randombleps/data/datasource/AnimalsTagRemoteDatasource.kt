package com.wolfcodea.randombleps.data.datasource

import com.wolfcodea.randombleps.core.utils.Constants
import com.wolfcodea.randombleps.core.endpoints.Endpoints
import com.wolfcodea.randombleps.core.exceptions.DataOrException
import com.wolfcodea.randombleps.data.models.AnimalsTags
import retrofit2.http.GET
import retrofit2.http.Headers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface IAnimalsTagApi {
    @Headers(
        "X-RapidAPI-Key: ${Constants.API_KEY}",
        "X-RapidAPI-Host: ${Constants.API_HOST}"
    )
    @GET(Endpoints.ANIMAL_TAGS)
    suspend fun getAnimalTags(): AnimalsTags
}

class AnimalsTagRemoteDatasource @Inject constructor(private val api: IAnimalsTagApi) {
    private val dataOrException = DataOrException<AnimalsTags, Boolean, Exception>()

    suspend fun getAnimalsTag(): DataOrException<AnimalsTags, Boolean, java.lang.Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAnimalTags()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false

        } catch (exception: Exception) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}

