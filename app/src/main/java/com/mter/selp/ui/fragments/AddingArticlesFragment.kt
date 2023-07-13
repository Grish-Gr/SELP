package com.mter.selp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.databinding.FragmentAddingArticlesBinding

class AddingArticlesFragment: BaseFragment() {
    private lateinit var binding: FragmentAddingArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddingArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()

    }

    private fun initAction(){
        binding.cancelAddingArticles.setOnClickListener {
            binding.titleArticles.text = null
            binding.textArticles.text = null
            openFragment(InformationFragment())
        }
        binding.saveArticles.setOnClickListener {
            openFragment(InformationFragment())
        }
    }
}