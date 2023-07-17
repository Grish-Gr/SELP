package com.mter.selp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mter.selp.AppSelp
import com.mter.selp.databinding.FragmentSignInBinding
import com.mter.selp.ui.fragments.BaseFragment
import com.mter.selp.viewmodels.DataModel
import com.mter.selp.ui.fragments.MainFragment
import com.mter.selp.viewmodels.AuthViewModel

class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentSignInBinding
    private val dataModel : DataModel by activityViewModels()
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        observeLogin()
    }

    private fun initAction(){
        binding.createProfile.setOnClickListener {
            openFragment(RegistrationFragment())
        }

        binding.signInButton.setOnClickListener {
            viewModel.loginInSystem(
                binding.signInEmail.editableText.toString(),
                binding.signInPassword.editableText.toString()
            )
            binding.progressLogin.visibility = View.VISIBLE
        }
    }

    private fun observeLogin() {
        viewModel.login.observe(this.viewLifecycleOwner) {
            openFragment(MainFragment())
            binding.progressLogin.visibility = View.INVISIBLE
        }
    }
}
