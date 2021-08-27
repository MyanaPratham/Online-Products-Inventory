package com.onlinemarket.front_end_layer.view.fragment.dashboard

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.onlinemarket.R
import com.onlinemarket.front_end_layer.activity.AddProductActivity
import com.onlinemarket.front_end_layer.activity.MainActivity
import com.onlinemarket.front_end_layer.view.adapter.ProductAdapter
import com.onlinemarket.integration_layer.db.DatabaseClient
import com.onlinemarket.integration_layer.db.Task
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.toolbar_dashbaord.*
import java.util.*


class DashboardFragment : Fragment() {

    var fab: FloatingActionButton? = null
    var listProduct: RecyclerView? = null
    var mTxtWarn:TextView?=null
    var editSearch:EditText?=null

    // Static Image list
    val imageIDs = arrayOf<Int>( //R.drawable.image1,
        R.drawable.ic_pepsi,
        R.drawable.ic_everday_cells,
        R.drawable.ic_pizza,
        R.drawable.ic_burger,
        R.drawable.ic_lays,
        R.drawable.ic_monaco,
        R.drawable.ic_parle,
        R.drawable.pd_4,
        R.drawable.pd_1,
        R.drawable.pd_2,
        R.drawable.pd_3,
        R.drawable.pd_4,
        R.drawable.pd_1,
        R.drawable.pd_2,
        R.drawable.pd_3,
        R.drawable.pd_4,
        R.drawable.pd_1,
        R.drawable.pd_2,
        R.drawable.pd_3,
        R.drawable.pd_4,
        R.drawable.pd_1,
        R.drawable.pd_2,
        R.drawable.pd_3,
        R.drawable.pd_4,
        R.drawable.pd_1,
        R.drawable.pd_2,
        R.drawable.pd_3,
        R.drawable.pd_4
    )
    var productAdapter: ProductAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        initView(root)
        onClick()

        // Get All saved data


        return root
    }

    override fun onResume() {
        super.onResume()

        getTasks()
    }

    fun initView(root: View) {


        listProduct = root.findViewById(R.id.listProduct)
        mTxtWarn=root.findViewById(R.id.txtWarn)


        // setting list view orientation
        var listTypeView =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        listProduct!!.layoutManager = listTypeView



        fab = root.findViewById(R.id.fab)
        editSearch = root.findViewById(R.id.editSearch)



    }

    fun onClick() {

        fab!!.setOnClickListener {

            val intent = Intent(activity, AddProductActivity::class.java)
            startActivity(intent)

        }
    }

    private fun getTasks() {
        class GetTasks :
            AsyncTask<Void?, Void?, List<Task>>() {


            override fun onPostExecute(productListData: List<Task>) {
                super.onPostExecute(productListData)

                // Intializing adapter

             //   Collections.reverse(productListData)



                productAdapter = ProductAdapter(productListData,activity as MainActivity, imageIDs, productListData)






                if(productListData.size>0)
                {
                    // Adding items to list view
                    listProduct!!.adapter = productAdapter
                    listProduct!!.visibility=View.VISIBLE
                    txtWarn!!.visibility=View.GONE

                    editSearch!!.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            productAdapter!!.getFilter()!!.filter(s);
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                            productAdapter!!.getFilter()!!.filter(s);


                        }
                    })
                }
                else {
                    listProduct!!.visibility=View.GONE
                    txtWarn!!.visibility=View.VISIBLE
                }
            }

            override fun doInBackground(vararg p0: Void?): List<Task> {
                return DatabaseClient
                    .getInstance(activity)
                    .appDatabase
                    .taskDao()
                    .all
            }
        }

        val gt = GetTasks()
        gt.execute()
    }
}