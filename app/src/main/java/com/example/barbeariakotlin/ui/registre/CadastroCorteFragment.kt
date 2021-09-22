package com.example.barbeariakotlin.ui.registre

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.example.barbeariakotlin.databinding.FragmentCadastroCorteBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.File
import java.io.IOException
import kotlin.jvm.Throws


class CadastroCorteFragment : Fragment() {

    private lateinit var mBinding: FragmentCadastroCorteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mBinding = FragmentCadastroCorteBinding.inflate(inflater)

        mBinding.comprovativo.setOnClickListener{

            mBinding.addImageCorte.visibility = View.VISIBLE
        }

        mBinding.exclusivo.setOnClickListener{

            mBinding.addImageCorte.visibility = View.GONE
        }

        mBinding.addImageCorte.setOnClickListener {
            openGalleryForImage()
        }

        mBinding.buttonSaveCorte.setOnClickListener {
                if (mBinding.exclusivo.isChecked)
                Snackbar.make(mBinding.root,"Salvo com sucesso", Snackbar.LENGTH_LONG).show()
                else
                Snackbar.make(mBinding.root,"É obrigatório anexar uma foto", Snackbar.LENGTH_LONG).show()

        }

        return mBinding.root

    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            imageView.setImageURI(data?.data) // handle chosen image
        }
    }

    val REQUEST_CODE = 100
}