package com.example.barbeariakotlin.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.barbeariakotlin.databinding.FragmentRegistreUserBinding
import com.google.android.material.snackbar.Snackbar

class RegistreUserFragment : Fragment() {

    private lateinit var registreUserViewModel: RegistreUserViewModel
    private lateinit var mBinding: FragmentRegistreUserBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registreUserViewModel =
            ViewModelProvider(this).get(RegistreUserViewModel::class.java)

        mBinding = FragmentRegistreUserBinding.inflate(inflater, container, false)

        mBinding.buttonRegistreUser.setOnClickListener {
            findNavController().navigate(RegistreUserFragmentDirections.actionNavRegistreUserToNavLogin())
            Snackbar.make(mBinding.root, "Usuário registrado com sucesso!!!", Snackbar.LENGTH_LONG).show()
        }

        registreUserViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}