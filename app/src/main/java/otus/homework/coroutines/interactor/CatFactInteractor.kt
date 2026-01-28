package otus.homework.coroutines.interactor

import otus.homework.coroutines.entity.FactEntity
import otus.homework.coroutines.entity.Result
import otus.homework.coroutines.mapper.FactDtoToEntityMapper
import otus.homework.coroutines.repository.CatFactsRepository

class CatFactInteractor(
    private val catsRepository: CatFactsRepository,
    private val factMapper: FactDtoToEntityMapper
) {
    suspend fun getCatFact(): Result<FactEntity> {
        return try {
            val catFact = catsRepository.getFact()
            val catImage = catsRepository.getImage()

            val entity = factMapper.map(catFact, catImage)
            Result.Success(entity)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}