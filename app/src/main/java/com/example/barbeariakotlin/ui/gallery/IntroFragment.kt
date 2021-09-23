package com.example.barbeariakotlin.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.barbeariakotlin.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {

    private lateinit var introViewModel: IntroViewModel
    private var _binding: FragmentIntroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        introViewModel =
            ViewModelProvider(this).get(IntroViewModel::class.java)

        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textIntroMelhor
        introViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        _binding!!.buttonIniciar1.setOnClickListener {
            findNavController().navigate(IntroFragmentDirections.actionNavIniciarToNavLogin())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}