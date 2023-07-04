package com.mter.selp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.R
import com.mter.selp.databinding.FragmentInfoAttackPanicBinding

class PanicAttackInfoFragment: BaseFragment() {
    private lateinit var binding: FragmentInfoAttackPanicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoAttackPanicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = resources.getString(R.string.info_about_panic_attack)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.contentInfoPanicAttack.text = Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT);
        } else {
            binding.contentInfoPanicAttack.text = Html.fromHtml(content);
        }
    }
}