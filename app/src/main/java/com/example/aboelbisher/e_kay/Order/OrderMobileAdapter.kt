package com.example.aboelbisher.e_kay.Order

import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import com.example.aboelbisher.e_kay.R


open class OrderMobileAdapter : RecyclerView.Adapter<OrderMobileAdapter.ViewHolder>() {

    var data: List<String> = listOf("MI5", "MI5S", "SAMSUNG S9", "NEXUS" , "ONE PLUS ONE" , "PIXEL" , "XIAMOE" , "SAMSUNG S9" ,
            "IPHONE X" , "IPHONE 8")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    lateinit var onClickDelegate: (pos: Int, str: String) -> Unit
    var selectedIndex = -1

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val checkedTextView = CheckedTextView(parent?.context).apply {
            this.setCheckMarkDrawable(R.drawable.check_box_state_drawble)
            val colorStateList = android.support.v4.content.ContextCompat.getColorStateList(context, R.color.order_selected_color)

            DrawableCompat.setTintList(this.checkMarkDrawable,colorStateList)


            this.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT)

            this.setPadding(0 , 50, 0 , 50)
            this.setTextColor(colorStateList)

        }
        return ViewHolder(checkedTextView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(data[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {



        init {
            itemView.setOnClickListener {
                onClickDelegate(adapterPosition, data[adapterPosition])
                if (itemView is CheckedTextView) {
                    if (!itemView.isChecked) {
                        itemView.isChecked = true
                        selectedIndex = adapterPosition
                        notifyDataSetChanged()

                    }
                }
            }
        }

        fun bind(str: String) {
            if (itemView is CheckedTextView) {
                itemView.text = str
                itemView.isChecked = (adapterPosition == selectedIndex)
            }

        }
    }
}