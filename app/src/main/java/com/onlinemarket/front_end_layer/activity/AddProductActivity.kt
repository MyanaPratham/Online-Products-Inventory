package com.onlinemarket.front_end_layer.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.onlinemarket.R
import com.onlinemarket.integration_layer.db.DatabaseClient
import com.onlinemarket.integration_layer.db.Task
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.toolbar_actvity.*


class AddProductActivity : AppCompatActivity() {
    val PICK_IMAGE = 1
    val PERMISSION_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_product)
        requestPermission()
        checkPermission()
        initView()

        onClick()
    }

    // Initialization of all view here
    fun initView() {}

    // manage all click events
    fun onClick() {

        txtBack.setOnClickListener { finish() }
        butCancel.setOnClickListener { finish() }


        butUpload.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }

        butSave.setOnClickListener {

            saveTask()
        }
    }

    private fun saveTask() {
        val sPdName: String = pdName.getText().toString().trim()
        val sDesc: String = pdDes.getText().toString().trim()
        val sCost: String = pdCost.getText().toString().trim()
//        val sFinishBy: String = editTextFinishBy.getText().toString().trim()
        if (sPdName.isEmpty()) {
            pdName.setError("Enter product name")
            pdName.requestFocus()
            return
        }
        if (sPdName.isEmpty()) {
            pdName.setError("Enter quantity")
            pdName.requestFocus()
            return
        }
        if (sDesc.isEmpty()) {
            pdDes.setError("Enter product description")
            pdDes.requestFocus()
            return
        }

// To Save data in local database we used this class
        class SaveTask :
            AsyncTask<Void?, Void?, Void?>() {


            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                Toast.makeText(applicationContext, "Your product is saved.", Toast.LENGTH_LONG).show()
            }

            override fun doInBackground(vararg p0: Void?): Void? {


                //creating a task
                val task =
                    Task()
                task.productName = sPdName
                task.desc = sDesc
                task.cost = sCost
//                task.isFinished = false

                //adding to database
                DatabaseClient.getInstance(applicationContext).appDatabase
                    .taskDao().insert(task)
                return null

            }
        }


        val st = SaveTask()
        st.execute()

    }



// Check permission for external storage


    fun checkPermission(): Boolean {

        var result: Int = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
        if (result == PackageManager.PERMISSION_GRANTED)

            return true
        else
            return false
    }

    // Request for permissions

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Toast.makeText(
                this,
                "Write External Storage permission allows us to save files. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }
    }


}