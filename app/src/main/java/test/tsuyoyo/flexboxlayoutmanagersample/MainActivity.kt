package test.tsuyoyo.flexboxlayoutmanagersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        val adapter = SimpleAdapter()
        recyclerView.adapter = adapter

        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        recyclerView.layoutManager = layoutManager
    }


    class SimpleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val TEXTS = arrayOf(
            "aaa", //aaaaaaaaaaaaaaaaaa",
            "bbb", //bbbbbbbbbbbbbbbbbb",
            "ccc", //cccccccccccccccccc",
            "ddd", //dddddddddddddddddd",
            "eeeeeeeeeeeeeeeeeeeee",
            "fffffffffffffffffffff",
            "ggggggggggggggggggggg",
            "hhhhhhhhhhhhhhhhhhhhh",
            "iiiiiiiiiiiiiiiiiiii",
            "jjjjjjjjjjjjjjjjjjjjj",
            "kkkkkkkkkkkkkkkkkkkkk"
        )

        val TYPE_100 = 0
        val TYPE_33 = 1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when(viewType) {
                TYPE_100 -> SimpleViewHolder100(parent)
                else -> SimpleViewHolder33(parent)
            }

        override fun getItemCount(): Int = TEXTS.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is SimpleViewHolder100 -> {
                    holder.setText(TEXTS[position])
                    val flexboxLp = holder.itemView.layoutParams as FlexboxLayoutManager.LayoutParams
                    flexboxLp.flexBasisPercent = 1.0f
                    holder.itemView.layoutParams = flexboxLp
                }
                is SimpleViewHolder33 -> {
                    holder.setText(TEXTS[position])
                    val flexboxLp = holder.itemView.layoutParams as FlexboxLayoutManager.LayoutParams
                    flexboxLp.flexBasisPercent = 0.33333f
                    holder.itemView.layoutParams = flexboxLp
                }
            }
        }

        override fun getItemViewType(position: Int): Int =
            when {
                (position % 4 == 0) -> TYPE_100
                else -> TYPE_33
            }
    }

    class SimpleViewHolder100(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_100, parent, false)
    ) {
        val textView: TextView = itemView.findViewById(R.id.text)

        fun setText(text: String) {
            textView.text = text
        }
    }

    class SimpleViewHolder33(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_33, parent, false)
    ) {
        val textView: TextView = itemView.findViewById(R.id.text)

        fun setText(text: String) {
            textView.text = text
        }
    }

}
