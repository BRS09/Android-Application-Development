package com.example.courseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_transaction.view.*
import java.text.DecimalFormat

class MyRecyclerViewAdapter(private val dataset: Array<String?>, private var transTotal: Double, private var totalView: TextView): RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    var i = 0
    var headerArray = arrayOf("Name", "Price", "Category", "Date")
    private val decimalFormat = DecimalFormat("$##,###.##")

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView

        init {
            textView = view.findViewById(R.id.text_info)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val testDouble = dataset[position].toString().toDoubleOrNull()
        if(i <= 3){
            holder.textView.text = headerArray[i]
            i += 1
        }
        else{
            if(testDouble != null){
                transTotal = transTotal.plus(testDouble)
                totalView.text = decimalFormat.format(transTotal)
                holder.textView.text = decimalFormat.format(testDouble)
            }
            else{
                holder.textView.text = dataset[position]
            }
        }
    }

    override fun getItemCount() = dataset.size

}