package otus.homework.coroutines.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import otus.homework.coroutines.presenter.CatsPresenter
import otus.homework.coroutines.R
import otus.homework.coroutines.entity.FactEntity

class CatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ICatsView {

    var presenter: CatsPresenter? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<Button>(R.id.button).setOnClickListener {
            presenter?.onInitComplete(context)
        }
    }

    override fun populate(fact: FactEntity) {
        findViewById<TextView>(R.id.catFact_textView).text = fact.fact
        Picasso.get()
            .load(fact.url)
            .into(findViewById<ImageView>(R.id.catImage_imageView))
    }
}

interface ICatsView {

    fun populate(fact: FactEntity)
}