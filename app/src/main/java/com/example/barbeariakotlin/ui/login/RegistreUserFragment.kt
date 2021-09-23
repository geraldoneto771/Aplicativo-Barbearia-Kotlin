package com.example.barbeariakotlin.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.barbeariakotlin.databinding.FragmentRegistreUserBinding

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

        registreUserViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}