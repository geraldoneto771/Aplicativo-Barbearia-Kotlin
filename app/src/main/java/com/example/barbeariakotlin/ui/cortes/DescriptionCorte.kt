package com.example.barbeariakotlin.ui.cortes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.barbeariakotlin.databinding.FragmentDescriptionCorteBinding


class DescriptionCorte : Fragment() {

    private lateinit var mBinding: FragmentDescriptionCorteBinding
    private lateinit var mViewModel: CorteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentDescriptionCorteBinding.inflate(inflater)
        mViewModel = ViewModelProvider(requireActivity()).get(CorteViewModel::class.java)


        // Inflate the layout for this fragment
        return mBinding.root
    }

}