package com.example.barbeariakotlin.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.barbeariakotlin.R
import com.example.barbeariakotlin.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var mBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        mBinding = FragmentLoginBinding.inflate(inflater, container, false)


        loginViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        mBinding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_nav_login_to_cadastro_corte)
        }

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}