package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mter.selp.databinding.FragmentRegisterBinding
import com.mter.selp.viewmodels.AuthViewModel

class RegistrationFragment : BaseFragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val dataModel : DataModel by activityViewModels()
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        observe()
    }

    private fun observe() {
        viewModel.registration.observe(this.viewLifecycleOwner) {
            Toast.makeText(
                this.context,
                "Подтвердите регистрацию. На ${it.email} пришла ссылка с подтверждением",
                Toast.LENGTH_LONG
            ).show()
            binding.progressRegistration.visibility = View.INVISIBLE
            openFragment(LoginFragment())
        }
    }

    fun initAction(){
        binding.create.setOnClickListener {
            viewModel.registration(
                getAuthService(),
                binding.registerEmail.editableText.toString(),
                binding.registerPassword.editableText.toString()
            )
            binding.progressRegistration.visibility = View.VISIBLE
        }

    }
}
