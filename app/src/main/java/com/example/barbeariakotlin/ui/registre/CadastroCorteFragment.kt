package com.example.barbeariakotlin.ui.registre

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.barbeariakotlin.R
import com.example.barbeariakotlin.databinding.FragmentCadastroCorteBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.nav_header_main.*


class CadastroCorteFragment : Fragment() {

    private lateinit var mBinding: FragmentCadastroCorteBinding

    private lateinit var imageView: ImageView

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
        startActivityForResult(intent, IMAGE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            mBinding.imgRegistreCorte.setImageURI(data?.data) // handle chosen image
        }
    }

}