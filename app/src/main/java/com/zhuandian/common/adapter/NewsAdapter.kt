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
import com.zhuandian.common.entity.NewsEntity

/**
 * desc :
 * author：xiedong
 * date：2019/7/19
 */
class NewsAdapter(var dataList: List<NewsEntity>, var context: Context?) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = dataList[position].title
        holder.tvContent.text = dataList[position].content
        holder.tvTime.text = dataList[position].time
        Glide.with(context).load(dataList[position].imgUrl).into(holder.img)
        var type: Int = dataList[position].type.toInt()
        holder.tvType.text = when (type) {
            1 -> "#热搜#"
            2 -> "#便宜购物#"
            3 -> "#促销#"
            4 -> "#网红推荐#"
            else
            -> "#未知#"
        }

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.iv_img)
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvContent = itemView.findViewById<TextView>(R.id.tv_content)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
        var tvType = itemView.findViewById<TextView>(R.id.tv_type)
    }
}