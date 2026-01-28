package otus.homework.coroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import otus.homework.coroutines.CrashMonitor
import otus.homework.coroutines.R
import otus.homework.coroutines.entity.Result
import otus.homework.coroutines.interactor.CatFactInteractor
import otus.homework.coroutines.view.ICatsView

class CatsViewModel(
    private val catsInteractor: CatFactInteractor
) : ViewModel() {

    private var _catsView: ICatsView? = null

    fun onInitComplete() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            CrashMonitor.trackWarning("Coroutine Exception", throwable)
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            when (val result = catsInteractor.getCatFact()) {
                is Result.Success -> _catsView?.populate(result.data)
                is Result.Error -> _catsView?.showToast(R.string.socket_timeout_error)
            }
        }
    }

    fun attachView(catsView: ICatsView) {
        _catsView = catsView
    }

    fun detachView() {
        _catsView = null
    }
}