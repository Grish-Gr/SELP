package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.R
import com.mter.selp.databinding.FragmentArticlesBinding

class ArticlesFragment : BaseFragment() {

        private lateinit var binding: FragmentArticlesBinding

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = FragmentArticlesBinding.inflate(inflater, container, false)
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

        private fun initAction() {
            binding.openVideoInfo.setOnClickListener {
                openFragment(VideoInfoFragment())
            }

            binding.back.setOnClickListener {
                openFragment(MainFragment())
            }

            binding.readArticle1.setOnClickListener {
                openFragment(InformationFragment())
            }

            binding.openAddingArticles.setOnClickListener {
                openFragment(AddingArticlesFragment())
            }
        }

}