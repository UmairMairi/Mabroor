package com.test.project.presentation.buy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.project.data.model.BuyDataModel
import com.test.project.databinding.ViewHolderBuyListBinding

class BuyListAdapter : RecyclerView.Adapter<BuyListAdapter.MyViewHolder>() {


    private var listener: ((BuyDataModel) -> Unit)? = null

    private var list = mutableListOf<BuyDataModel>()

    fun setContentList(list: MutableList<BuyDataModel>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ViewHolderBuyListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            ViewHolderBuyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.buy = this.list[position]

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}