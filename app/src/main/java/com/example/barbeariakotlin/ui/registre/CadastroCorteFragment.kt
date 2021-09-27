package com.example.barbeariakotlin.ui.registre

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.barbeariakotlin.databinding.FragmentCadastroCorteBinding
import com.example.barbeariakotlin.model.CorteModel
import com.example.barbeariakotlin.ui.cortes.CorteViewModel
import com.google.android.material.snackbar.Snackbar

class CadastroCorteFragment : Fragment() {

    private lateinit var mBinding: FragmentCadastroCorteBinding

    private lateinit var imageView: ImageView
    private lateinit var mViewModel: CorteViewModel

    private var validateImage: Int = 0

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }
    /*val uriPathHelper = URIPathHelper()
    val filePath = uriPathHelper.getPath(this, YOUR_URI_OBJECT)
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mBinding = FragmentCadastroCorteBinding.inflate(inflater)

        mViewModel = ViewModelProvider(requireActivity()).get(CorteViewModel::class.java)

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

                val name = mBinding.editNameCorte.text.toString()
                val description = mBinding.editDescriptionCorte.text.toString()
                val price = mBinding.editPriceCorte.text.toString().toFloat()

                val corte = CorteModel()

                if (mBinding.exclusivo.isChecked && (validateImage == 0)){

                corte.name = name
                    corte.description = description
                    corte.price = price
                    corte.status = false

                    mViewModel.createCorte(corte)

                    Snackbar.make(mBinding.root,"Salvo com sucesso!", Snackbar.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                else if (mBinding.comprovativo.isChecked && (validateImage == 1)){
                    corte.name = name
                    corte.description = description
                    corte.price = price
                    corte.status = true

                    mViewModel.createCorte(corte)

                    Snackbar.make(mBinding.root,"Salvo com sucesso!", Snackbar.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                else if (mBinding.comprovativo.isChecked && (validateImage == 0)){
                    Snackbar.make(mBinding.root,"É obrigatório adicionar uma imagem!", Snackbar.LENGTH_LONG).show()
                }

        }

        return mBinding.root

    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            mBinding.imgRegistreCorte.setImageURI(data?.data) // handle chosen image
            validateImage = 1

        }
    }

}