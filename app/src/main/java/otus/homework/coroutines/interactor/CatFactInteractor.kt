package otus.homework.coroutines.interactor

import otus.homework.coroutines.entity.FactEntity
import otus.homework.coroutines.mapper.FactDtoToEntityMapper
import otus.homework.coroutines.repository.CatFactsRepository

class CatFactInteractor(
    private val catsRepository: CatFactsRepository,
    private val factMapper: FactDtoToEntityMapper
) {
    suspend fun getCatFact(): FactEntity {
        val catFact = catsRepository.getFact()
        val catImage = catsRepository.getImage()

        return factMapper.map(catFact, catImage)
    }
}