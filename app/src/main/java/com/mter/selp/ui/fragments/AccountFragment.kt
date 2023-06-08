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
            openFragment(MoodFragment())
        }

        binding.premium.setOnClickListener {

        }

        binding.theme.setOnClickListener {
            if (binding.themeColor.text == "светлая"){
                binding.themeColor.setText("тёмная")
            } else{
                binding.themeColor.setText("светлая")
            }
        }

        binding.notification.setOnClickListener {
            if (binding.statusNotification.text == "off"){
                binding.statusNotification.setText("on")
            } else {
                binding.statusNotification.setText("off")
            }
        }

        binding.language.setOnClickListener {
            if (binding.statusLanguage.text == "Русский"){
                binding.statusLanguage.setText("English")
            } else{
                binding.statusLanguage.setText("Русский")
            }
        }


    }
}
