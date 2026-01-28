package otus.homework.coroutines.service

import otus.homework.coroutines.dto.FactDto
import retrofit2.http.GET

interface CatFactService {

    @GET("fact")
    suspend fun getCatFact(): FactDto
}