package com.test.project.presentation.sell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.project.data.model.SellDataModel
import com.test.project.data.room.ItemToSell
import com.test.project.databinding.ViewHolderSellListBinding

class SellListAdapter : RecyclerView.Adapter<SellListAdapter.MyViewHolder>() {


    private var listener: ((SellDataModel) -> Unit)? = null

    private var list = mutableListOf<SellDataModel>()

    fun setContentList(list: MutableList<ItemToSell>) {
        val data = list.map { item ->
            SellDataModel(
                id = item.id,
                name = item.name,
                price = item.price,
                quantity = item.quantity,
                type = item.type
            )
        }.toMutableList()

        this.list = data
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ViewHolderSellListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            ViewHolderSellListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.sell = this.list[position]

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}