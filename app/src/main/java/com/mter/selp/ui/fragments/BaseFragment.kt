package com.mter.selp.ui.fragments

import androidx.fragment.app.Fragment
import com.mter.selp.R

open class BaseFragment: Fragment() {
    protected fun openFragment(fragment: BaseFragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}