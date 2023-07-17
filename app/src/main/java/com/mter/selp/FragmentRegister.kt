package com.mter.selp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mter.selp.databinding.FragmentRegisterBinding
import com.mter.selp.ui.fragments.BaseFragment
import com.mter.selp.ui.fragments.MainFragment
import com.mter.selp.viewmodels.DataModel

class FragmentRegister : BaseFragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val dataModel : DataModel by activityViewModels()

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
    }

    fun initAction(){
        binding.create.setOnClickListener {
            openFragment(MainFragment())
            dataModel.name.value = binding.profileName.text.toString()
            dataModel.mail.value = binding.registerEmail.text.toString()
        }

    }
}
