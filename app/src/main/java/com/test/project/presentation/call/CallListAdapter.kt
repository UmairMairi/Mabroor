package com.test.project.presentation.call

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.project.data.model.CallDataModel
import com.test.project.databinding.ViewHolderCallListBinding


class CallListAdapter : RecyclerView.Adapter<CallListAdapter.MyViewHolder>() {

    private var listener :((CallDataModel)->Unit)?=null

    var list = mutableListOf<CallDataModel>()

    fun setContentList(list: MutableList<CallDataModel>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ViewHolderCallListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            ViewHolderCallListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(CallDataModel)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: CallListAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.call = this.list[position]

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}