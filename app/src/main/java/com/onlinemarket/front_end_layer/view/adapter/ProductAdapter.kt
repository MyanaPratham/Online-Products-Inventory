package com.onlinemarket.front_end_layer.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filter.FilterResults
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onlinemarket.R
import com.onlinemarket.front_end_layer.activity.MainActivity
import com.onlinemarket.front_end_layer.activity.ProductDetailPageActivity
import com.onlinemarket.integration_layer.db.Task
import java.util.*

class ProductAdapter(
    var contactListFiltered: List<Task?>,
    val context: MainActivity,
    var myDataList: Array<Int>,
    var productDetailList: List<Task>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_products, viewGroup, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return productDetailList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        //   val rand = Random()
//      holder.imgProduct.setImageResource(rand.nextInt(myDataList.size))

        // we are using Try catch because right now we have limited static images ,
        // if we add more products app may crash when there is no image for that product item
        try {
            holder.imgProduct.setImageResource(myDataList.get(position))
        } catch (e: Exception) {
            e.printStackTrace()
        }


        // setting data to views
        holder.txtProductName.setText("" + productDetailList.get(position).productName)
        holder.txtCost.setText("Qty:" + productDetailList.get(position).cost)
        holder.txtDes.setText("" + productDetailList.get(position).desc)


        // click  to go on detail page
        holder.itemView.setOnClickListener {

            val intent = Intent(context, ProductDetailPageActivity::class.java)
            intent.putExtra("cost", productDetailList.get(position).cost)
            intent.putExtra("name", productDetailList.get(position).productName)
            intent.putExtra("des", productDetailList.get(position).desc)
           // context.startActivity(intent)
        }





    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        var txtProductName: TextView = view.findViewById(R.id.txtProductName)
        var txtCost: TextView = view.findViewById(R.id.txtProductCost)
        var txtDes: TextView = view.findViewById(R.id.txtProductDes)


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                     contactListFiltered  = productDetailList
                } else {
                    val filteredList: MutableList<Task> = ArrayList()
                    for (row in productDetailList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.productName.toLowerCase()
                                .contains(charString.toLowerCase()) || row.desc
                                .contains(charSequence)
                        ) {
                            filteredList.add(row)
                        }
                    }
                    contactListFiltered = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = contactListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                contactListFiltered = filterResults.values as ArrayList<Task?>
                notifyDataSetChanged()
            }
        }
    }


}



