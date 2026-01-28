package otus.homework.coroutines.repository

import otus.homework.coroutines.dto.FactDto
import otus.homework.coroutines.dto.ImageDto

interface CatFactsRepository {

    suspend fun getFact(): FactDto

    suspend fun getImage(): ImageDto
}