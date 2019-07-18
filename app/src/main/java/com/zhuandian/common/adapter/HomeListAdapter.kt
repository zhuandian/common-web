package com.zhuandian.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhuandian.common.R
import com.zhuandian.common.entity.HomeEntity
import com.zhuandian.common.entity.HomeList

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */
class HomeListAdapter(var dataList: List<HomeList>, var context: Context?) : RecyclerView.Adapter<HomeListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = dataList[position].title
        holder.tvContent.text = dataList[position].content
        holder.tvTime.text = dataList[position].time
        Glide.with(context).load(dataList[position].imgUrl).into(holder.img)
        holder.itemView.setOnClickListener {
            clickListener?.onItemClick(dataList[position])
        }
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.iv_img)
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvContent = itemView.findViewById<TextView>(R.id.tv_content)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
    }

    private var clickListener: OnItemClickListener? = null
    fun setOnClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(homeList: HomeList)
    }
}
