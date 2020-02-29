package com.app.jet2test.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.jet2test.R
import com.app.jet2test.model.EmpDataModel
import com.app.jet2test.view.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_emp.view.*


class EmpAdapter(private val context: Context,private val empList: MutableList<EmpDataModel>) : RecyclerView.Adapter<EmpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_emp, parent, false)
        return ViewHolder(context,v)
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(empList[position])
    }

    class ViewHolder(private val context: Context,private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(items: EmpDataModel) {
            itemView?.empName.text = items?.employeeName
            Glide.with(view?.context)
                ?.load(items?.profileImage)
                ?.placeholder(R.mipmap.ic_launcher)
                ?.apply(
                    RequestOptions.overrideOf(
                        view.context.resources.getDimension(R.dimen.image_width).toInt(),
                        view.context.resources.getDimension(R.dimen.image_height).toInt()
                    )
                )
                ?.into(itemView?.empImage)

            view.setOnClickListener(View.OnClickListener {
                val intent = Intent(context,DetailActivity::class.java)
                intent.putExtra("emp", items)
                context.startActivity(intent)
            })
        }
    }
}