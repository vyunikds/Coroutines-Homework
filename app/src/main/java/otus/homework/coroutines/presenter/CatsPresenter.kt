package otus.homework.coroutines.presenter

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import otus.homework.coroutines.CrashMonitor
import otus.homework.coroutines.R
import otus.homework.coroutines.interactor.CatFactInteractor
import otus.homework.coroutines.view.ICatsView
import java.net.SocketTimeoutException

class CatsPresenter(
    private val catsInteractor: CatFactInteractor,
    private val presenterScope: CoroutineScope
) {

    private var _catsView: ICatsView? = null

    fun onInitComplete(context: Context) {
        presenterScope.launch {
            try {
                _catsView?.populate(catsInteractor.getCatFact())
            } catch (_: SocketTimeoutException) {
                showToast(context, context.getString(R.string.socket_timeout_error))
            } catch (e: Exception) {
                CrashMonitor.trackWarning()
                showToast(context, e.message.toString())
            }
        }
    }

    fun attachView(catsView: ICatsView) {
        _catsView = catsView
    }

    fun detachView() {
        _catsView = null
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}