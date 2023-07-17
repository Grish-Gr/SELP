package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mter.selp.databinding.FragmentPremiumBinding
import com.mter.selp.viewmodels.DataModel

class PremiumFragment   : BaseFragment() {
    private lateinit var binding: FragmentPremiumBinding
    private val dataModel : DataModel by activityViewModels()
    var selpPremium = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremiumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction(){
        binding.cancelBuy.setOnClickListener {
            selpPremium = false
            dataModel.selpPremium.value = selpPremium
            openFragment(AccountFragment())
        }
        binding.acceptBuy.setOnClickListener {
            selpPremium = true
            dataModel.selpPremium.value = selpPremium
            openFragment(AccountFragment())
        }
    }
}