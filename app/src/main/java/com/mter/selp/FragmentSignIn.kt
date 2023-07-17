package com.mter.selp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mter.selp.databinding.FragmentSignInBinding
import com.mter.selp.ui.fragments.BaseFragment
import com.mter.selp.viewmodels.DataModel
import com.mter.selp.ui.fragments.MainFragment

class FragmentSignIn : BaseFragment() {
    private lateinit var binding: FragmentSignInBinding
    private val dataModel : DataModel by activityViewModels()

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
    }

    fun initAction(){
        binding.createProfile.setOnClickListener {
            openFragment(FragmentRegister())
        }

        binding.signInButton.setOnClickListener {
            openFragment(MainFragment())
            dataModel.mail.value = binding.signInEmail.text.toString()
        }
    }
}
