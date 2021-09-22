package com.example.barbeariakotlin.ui.registre

import android.Manifest
import android.content.pm.PackageManager
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

        mBinding.addImageCorte.setOnClickListener {
            if (allPermissionsGranted()) {
                dispatchTakePictureIntent()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
                )
            }
        }

        return mBinding.root

    }

    val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {

            if (it) {
                Snackbar.make(mBinding.root, "Anexado com sucesso", Snackbar.LENGTH_LONG).show()

                findNavController().navigateUp()
            } else {
                Snackbar.make(mBinding.root, "É obrigatório anexar uma foto", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    var photoFile: File? = null
    lateinit var photoURI: Uri

    private fun dispatchTakePictureIntent() {
        photoFile = try {
            createImageFile()
        } catch (ex: IOException) {
            null
        }
        photoFile?.also {
            photoURI = FileProvider.getUriForFile(
                requireContext(), "", it
            )
            takePicture.launch(photoURI)
        }
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val name: String = "photo"
        val storageDir: File? = getOutputDirectory()
        return File.createTempFile(
            name, /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private val REQUEST_CODE_PERMISSIONS = 10
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                dispatchTakePictureIntent()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    fun getOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, "project").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir


    }

}