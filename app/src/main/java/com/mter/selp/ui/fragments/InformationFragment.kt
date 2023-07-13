package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.R
import com.mter.selp.databinding.FragmentInformationBinding

class InformationFragment: BaseFragment() {
    private lateinit var binding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        showHintDialog(
            message = resources.getString(R.string.message_hint_note_dialog),
            actionCancel = {
                enabledHintFragment(NOTE_HINT_DIALOG_ENABLED, false)
            },
            codeDialog = NOTE_HINT_DIALOG_ENABLED
        )
    }

    private fun initAction(){
        binding.openVideoInfo.setOnClickListener {
            openFragment(VideoInfoFragment())
        }

        binding.back.setOnClickListener {
            openFragment(MainFragment())
        }

        binding.openAddingArticles.setOnClickListener {
            openFragment(AddingArticlesFragment())
        }
    }
}
