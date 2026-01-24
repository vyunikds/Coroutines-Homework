package otus.homework.coroutines.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import otus.homework.coroutines.R
import otus.homework.coroutines.entity.FactEntity
import otus.homework.coroutines.viewmodel.CatsViewModel

class CatsView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ICatsView {

    var viewModel: CatsViewModel? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel?.onInitComplete()
        }
    }

    override fun populate(fact: FactEntity) {
        findViewById<TextView>(R.id.catFact_textView).text = fact.fact
        Picasso.get()
            .load(fact.url)
            .into(findViewById<ImageView>(R.id.catImage_imageView))
    }

    override fun showToast(messageResId: Int) {
        showToast(context.getString(messageResId))
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

interface ICatsView {

    fun populate(fact: FactEntity)
    fun showToast(messageResId: Int)
    fun showToast(message: String)
}