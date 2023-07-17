package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.databinding.FragmentSignBinding

class StartLoginFragment : BaseFragment() {
    private lateinit var binding: FragmentSignBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    fun initAction(){
        binding.signIn.setOnClickListener {
            openFragment(LoginFragment())
        }

        binding.register.setOnClickListener {
            openFragment(RegistrationFragment())
        }
    }
}