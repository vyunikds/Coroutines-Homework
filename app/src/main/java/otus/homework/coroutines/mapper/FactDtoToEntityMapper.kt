package otus.homework.coroutines.mapper

import otus.homework.coroutines.dto.FactDto
import otus.homework.coroutines.dto.ImageDto
import otus.homework.coroutines.entity.FactEntity

class FactDtoToEntityMapper {

    fun map(factDto: FactDto, imageDto: ImageDto): FactEntity {
        return FactEntity(factDto.fact, imageDto.url)
    }
}