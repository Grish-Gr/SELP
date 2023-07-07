package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.mter.selp.databinding.FragmentAccountBinding

class AccountFragment : BaseFragment() {
    private lateinit var binding: FragmentAccountBinding
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.name.observe(activity as LifecycleOwner) {
            binding.userName.text = it
        }

        dataModel.mail.observe(activity as LifecycleOwner) {
            binding.userMail.text = it
        }

        initAction()
    }

    private fun initAction(){

        binding.changeInfo.setOnClickListener {

        }

        binding.back.setOnClickListener {
            openFragment(MainFragment())
        }

        binding.result.setOnClickListener {
            openFragment(ProgressFragment())
        }

        binding.premium.setOnClickListener {
            openFragment(PremiumFragment())
        }

    }
}
