package otus.homework.coroutines.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import otus.homework.coroutines.dto.FactDto
import otus.homework.coroutines.dto.ImageDto
import otus.homework.coroutines.service.CatFactService
import otus.homework.coroutines.service.TheCatApiService

class CatsRepositoryImpl(
    private val catFactService: CatFactService,
    private val theCatApiService: TheCatApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CatFactsRepository {
    override suspend fun getFact(): FactDto {
        return withContext(dispatcher) {
            catFactService.getCatFact()
        }
    }

    override suspend fun getImage(): ImageDto {
        return withContext(dispatcher) {
            theCatApiService.getCatImageSearch().first()
        }
    }
}
