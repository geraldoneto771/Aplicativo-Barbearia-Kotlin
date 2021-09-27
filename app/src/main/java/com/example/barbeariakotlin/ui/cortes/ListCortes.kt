package com.example.barbeariakotlin.ui.cortes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.barbeariakotlin.databinding.FragmentListCortesBinding


class ListCortes : Fragment() {

    private lateinit var mBinding: FragmentListCortesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentListCortesBinding.inflate(inflater)

        mBinding.createCorte.setOnClickListener {
            findNavController().navigate(ListCortesDirections.actionListCortesToCadastroCorte())
        }


        return mBinding.root
    }

}