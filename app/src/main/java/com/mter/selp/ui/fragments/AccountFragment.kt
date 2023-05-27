package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.databinding.FragmentAccountBinding


class AccountFragment : BaseFragment() {
    private lateinit var binding: FragmentAccountBinding

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
            if (binding.themeColor.text == "Светлая"){
                binding.themeColor.setText("Тёмная")
            } else{
                binding.themeColor.setText("Светлая")
            }
        }

        binding.notification.setOnClickListener {
            if (binding.statusNotification.text == "Выключены"){
                binding.statusNotification.setText("Включены")
            } else {
                binding.statusNotification.setText("Выключены")
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