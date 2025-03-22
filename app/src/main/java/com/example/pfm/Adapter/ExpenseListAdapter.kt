package com.example.pfm.Adapter

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pfm.Domain.ExpenseDomain
import com.example.pfm.databinding.ViewholderItemsBinding

class ExpenseListAdapter(private val items: MutableList<ExpenseDomain>):
    RecyclerView.Adapter<ExpenseListAdapter.Viewholer>() {

    class Viewholer(val binding:ViewholderItemsBinding):RecyclerView.ViewHolder(binding.root)


    private lateinit var context:Context
    var formatter: DecimalFormat? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseListAdapter.Viewholer {
        context=parent.context
        formatter=DecimalFormat("###,###,###.##")
        val binding=ViewholderItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholer(binding)
    }

    override fun onBindViewHolder(holder: ExpenseListAdapter.Viewholer, position: Int) {
        val item=items[position]
          holder.binding.TitleTxt.text=item.title
          holder.binding.TimeTxt.text=item.time
          holder.binding.PriceTxt.text=formatter?.format(item.price)+"vnd"
        val drawableResource=holder.itemView.resources.getIdentifier(item.pic,"drawable",context.packageName)
        Glide.with(context)
            .load(drawableResource)
            .into(holder.binding.pic)

    }

    override fun getItemCount(): Int=items.size

}