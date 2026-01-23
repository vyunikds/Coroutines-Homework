package otus.homework.coroutines.service

import otus.homework.coroutines.dto.ImageDto
import retrofit2.http.GET

interface TheCatApiService {

    @GET("images/search")
    suspend fun getCatImageSearch(): List<ImageDto>
}