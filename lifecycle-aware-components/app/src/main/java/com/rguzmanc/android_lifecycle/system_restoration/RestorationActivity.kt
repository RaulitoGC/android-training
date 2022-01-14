package com.rguzmanc.android_lifecycle.system_restoration

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rguzmanc.android_lifecycle.R
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventActivity
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventModelFactory
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventViewModel
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat

import android.app.Activity
import android.app.AlertDialog
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent

import android.content.DialogInterface
import android.database.Cursor
import android.provider.MediaStore
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.os.Environment
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.rguzmanc.android_lifecycle.MainActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class RestorationActivity: AppCompatActivity() {

    companion object{
        val TAG = RestorationActivity::class.java.name

        const val REQUEST_ID_MULTIPLE_PERMISSIONS = 101
    }

    private val viewModel: RestorationViewModel by viewModels()

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var imageView: ImageView

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restorationn)
        Log.d(TAG, "onCreate()")
        initUi()
        initViewModel()

        if(savedInstanceState != null) {
            Log.d(TAG, "onSaveInstanceState not null")
            savedInstanceState.getString("BITMAP_PATH")?.let { path ->
                Log.d(TAG, "RESTORATION path = $path")
                val file = File(path)
                if(file.exists()){
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    Log.d(TAG, "bitmap = $bitmap")
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun initUi() {
        textView = findViewById(R.id.tvContent)
        button = findViewById(R.id.btn_generate)
        imageView = findViewById(R.id.img_view)
        button.setOnClickListener {
            //getContent.launch("images/*")
            if(checkAndRequestPermissions(REQUEST_ID_MULTIPLE_PERMISSIONS)){
                chooseImage()
            }
        }
    }

    private fun initViewModel() {
        viewModel.textViewContent.observe(this, {
            Log.d(TAG, "updating textview")
            textView.text = it
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {


        if(imageView.drawable != null) {
            imageView.drawable.toBitmap()?.let { bitmap ->
                Log.d(TAG, "drawable not null")

                bitmapToFile(bitmap, "new_file_name_${System.currentTimeMillis()}.jpg")?.let { file ->
                    Log.d(TAG, "get file")
                    outState.putString("BITMAP_PATH", file.absolutePath)
                }
            }
        }

        Log.d(TAG, "save instance state")
        super.onSaveInstanceState(outState)
    }

    private fun bitmapToFile(bitmap: Bitmap?, fileNameToSave: String): File? { // File name like "image.png"
        //create a file to write bitmap data
        if(bitmap == null) return null
        var file: File? = null
        return try {
            file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileNameToSave)
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

    private fun chooseImage() {
        val optionsMenu = arrayOf<CharSequence>(
            "Take Photo",
            "Choose from Gallery",
            "Exit"
        ) // create a menuOption Array
        // create a dialog for showing the optionsMenu
        val builder =  AlertDialog.Builder(this)
        // set the items in builder
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when {
                optionsMenu[i] == "Take Photo" -> {
                    // Open the camera and get the photo
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePicture, 0)
                }
                optionsMenu[i] == "Choose from Gallery" -> {
                    // choose from  external storage
                    val pickPhoto =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(pickPhoto, 1)
                }
                optionsMenu[i] == "Exit" -> {
                    dialogInterface.dismiss()
                }
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.extras!!["data"] as Bitmap?
                    imageView.setImageBitmap(selectedImage)
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor: Cursor? =
                            contentResolver.query(selectedImage, filePathColumn, null, null, null)
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            val picturePath: String = cursor.getString(columnIndex)
                            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                            cursor.close()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e(MainActivity.TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.e(MainActivity.TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.e(MainActivity.TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(MainActivity.TAG, "onDestroy()")
    }

}