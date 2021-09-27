package com.example.barbeariakotlin.ui.cortes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barbeariakotlin.R
import com.example.barbeariakotlin.databinding.FragmentListCortesBinding
import com.example.barbeariakotlin.databinding.ItemCorteUI
import com.example.barbeariakotlin.model.CorteModel


class ListCortes : Fragment() {

    private lateinit var mBinding: FragmentListCortesBinding
    private lateinit var mViewModel: CorteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentListCortesBinding.inflate(inflater)

        mViewModel = ViewModelProvider(requireActivity()).get(CorteViewModel::class.java)

        mViewModel.getAllCorte()

        mBinding.createCorte.setOnClickListener {
            findNavController().navigate(ListCortesDirections.actionListCortesToCadastroCorte())
        }

        mViewModel.cortes.observe(viewLifecycleOwner, Observer {
            mBinding.recyclerView.adapter = CorteAdapter(it)
            mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        })

        return mBinding.root
    }

    inner class CorteAdapter(val list: ArrayList<CorteModel>):
            RecyclerView.Adapter<CorteAdapter.CorteViewHolder>() {

                inner class CorteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
                    var bind: ItemCorteUI

                    init {
                        bind = ItemCorteUI.bind(itemView)
                    }
                }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorteViewHolder {
            return CorteViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_corte, parent, false)
            )
        }

        override fun onBindViewHolder(holder: CorteViewHolder, position: Int) {
           val corte = list[position]

            holder.bind.fieldName.text = corte.name
            holder.bind.price.text = corte.price.toString()
        }

        override fun getItemCount(): Int {
            return list.size
        }


    }

}