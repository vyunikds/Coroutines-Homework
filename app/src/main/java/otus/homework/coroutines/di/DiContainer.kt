package otus.homework.coroutines.di

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import otus.homework.coroutines.interactor.CatFactInteractor
import otus.homework.coroutines.mapper.FactDtoToEntityMapper
import otus.homework.coroutines.repository.CatFactsRepository
import otus.homework.coroutines.repository.CatsRepositoryImpl
import otus.homework.coroutines.service.CatFactService
import otus.homework.coroutines.service.TheCatApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiContainer {

    val catFactInteractor by lazy {
        CatFactInteractor(catsRepository, FactDtoToEntityMapper())
    }

    private val catsRepository: CatFactsRepository by lazy {
        CatsRepositoryImpl(catFactService, theCatApiService)
    }

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }
    private val catFactRetrofit by lazy {
        retrofitBuilder
            .baseUrl("https://catfact.ninja/")
            .build()
    }

    private val theCatApiRetrofit by lazy {
        retrofitBuilder
            .baseUrl("https://api.thecatapi.com/v1/")
            .build()
    }

    val catFactService: CatFactService by lazy {
        catFactRetrofit.create(CatFactService::class.java)
    }

    val theCatApiService: TheCatApiService by lazy {
        theCatApiRetrofit.create(TheCatApiService::class.java)
    }
    val presenterScope by lazy {
        CoroutineScope(
            context = SupervisorJob() + Dispatchers.Main + CoroutineName("CatsCoroutine")
        )
    }
}