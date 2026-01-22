package otus.homework.coroutines

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class CatsPresenter(
    private val catsService: CatsService,
    private val presenterScope: CoroutineScope
) {

    private var _catsView: ICatsView? = null

    fun onInitComplete(context: Context) {
        presenterScope.launch {
            try {
                val fact = withContext(Dispatchers.IO) {
                    catsService.getCatFact()
                }
                _catsView?.populate(fact)
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