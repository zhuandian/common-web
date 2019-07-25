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
import com.zhuandian.common.entity.NewsEntity

/**
 * desc :
 * author：xiedong
 * date：2019/7/19
 */
class NewsAdapter(var dataList: List<NewsEntity>, var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val SINGLE_IMG: Int = 1; //单图布局
    private val MULTI_IMG: Int = 2; //多图布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val singleImgView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news_single_img, parent, false)
        val multiImgView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news_multi_img, parent, false)
        if (viewType == MULTI_IMG)
            return MyMultiViewHolder(multiImgView)
        else
            return MySingleViewHolder(singleImgView)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
//        return SINGLE_IMG
        //TODO 多图布局暂时不做，看起来不好看
        if (dataList.get(position).imgUrl.size > 1) return MULTI_IMG else return SINGLE_IMG
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == SINGLE_IMG) {
            viewHolder as MySingleViewHolder

            viewHolder.tvTitle.text = dataList[position].title
            viewHolder.tvContent.text = dataList[position].content
            viewHolder.tvTime.text = dataList[position].time
            Glide.with(context).load(dataList[position].imgUrl[0]).into(viewHolder.img)
            var type: Int = dataList[position].type.toInt()
            viewHolder.tvType.text = when (type) {
                1 -> "#热搜#"
                2 -> "#便宜购物#"
                3 -> "#促销#"
                4 -> "#网红推荐#"
                else
                -> "#未知#"
            }
            viewHolder.itemView.setOnClickListener {
                clickListener?.onItemClick(dataList[position])
            }
        } else {
            viewHolder as MyMultiViewHolder

            viewHolder.tvTitle.text = dataList[position].title
            viewHolder.tvContent.text = dataList[position].content
            viewHolder.tvTime.text = dataList[position].time
            var type: Int = dataList[position].type.toInt()
            viewHolder.tvType.text = when (type) {
                1 -> "#热搜#"
                2 -> "#便宜购物#"
                3 -> "#促销#"
                4 -> "#网红推荐#"
                else
                -> "#未知#"
            }
            viewHolder.itemView.setOnClickListener {
                clickListener?.onItemClick(dataList[position])
            }

            var windowManager: WindowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            var width: Int = windowManager.defaultDisplay.width
            viewHolder.imgContainer.removeAllViews()
            var i: Int = 0
            while (i < dataList.get(position).imgUrl.size) {
                var imageView = ImageView(context)
                imageView.layoutParams = LinearLayout.LayoutParams(width/2,LinearLayout.LayoutParams.MATCH_PARENT)
                imageView.scaleType=ImageView.ScaleType.FIT_XY
                imageView.setPadding(5,5,5,5)
                Glide.with(context).load(dataList[position].imgUrl[i]).into(imageView)
                viewHolder.imgContainer.addView(imageView)
                i++
            }
        }

    }


    inner class MySingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.iv_img)
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvContent = itemView.findViewById<TextView>(R.id.tv_content)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
        var tvType = itemView.findViewById<TextView>(R.id.tv_type)
    }

    inner class MyMultiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgContainer = itemView.findViewById<LinearLayout>(R.id.ll_img_contanier)
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvContent = itemView.findViewById<TextView>(R.id.tv_content)
        var tvTime = itemView.findViewById<TextView>(R.id.tv_time)
        var tvType = itemView.findViewById<TextView>(R.id.tv_type)
    }

    private var clickListener: OnItemClickListener? = null
    fun setOnClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(newsEntity: NewsEntity)
    }
}