package com.zhuandian.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhuandian.common.R
import com.zhuandian.common.entity.PictureEntity

/**
 * desc :
 * author：xiedong
 * date：2019/7/23
 */
class PictureAdapter(var dataList: List<PictureEntity>, var context: Context?) : RecyclerView.Adapter<PictureAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvDesc.text = dataList.get(position).desc
        Glide.with(context).load(dataList.get(position).img).into(holder.ivImg)

        var windowManager: WindowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var width: Int = windowManager.defaultDisplay.width

        holder.ivImg.layoutParams = LinearLayout.LayoutParams(width / 2, LinearLayout.LayoutParams.MATCH_PARENT)
        holder.iv_share.setOnClickListener {
            clickListener?.shareClick(dataList[position])
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivImg = itemView.findViewById<ImageView>(R.id.iv_img)
        var tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
        var iv_share = itemView.findViewById<ImageView>(R.id.iv_share)
    }


    private var clickListener: OnShareClickListener? = null

    fun setOnShareClickListener(clickListener: OnShareClickListener) {
        this.clickListener = clickListener
    }

    interface OnShareClickListener {
        fun shareClick(pictureEntity: PictureEntity)
    }
}