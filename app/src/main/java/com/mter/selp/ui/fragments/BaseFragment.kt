package com.mter.selp.ui.fragments

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.mter.selp.AppSelp
import com.mter.selp.R
import com.mter.selp.data.network.AuthRestService
import com.mter.selp.data.network.UserRestService

open class BaseFragment: Fragment() {

    protected fun openFragment(fragment: BaseFragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .addToBackStack("Main")
            .commit()
    }

    protected fun showHintDialog(
        title: String = resources.getString(R.string.notification),
        message: String,
        titleConfirm: String =  resources.getString(R.string.understand),
        titleCancel: String = resources.getString(R.string.off_this_notification),
        actionConfirm: () -> Unit = {},
        actionCancel: () -> Unit = {},
        codeDialog: String? = null
    ){
        if (!CODES_HINT_DIALOG.contains(codeDialog)) {
            return
        }
        val settings = this.activity?.getSharedPreferences(SETTINGS_HINT_DIALOGS, Context.MODE_PRIVATE)
        if (settings?.getBoolean(codeDialog, true) == false) {
            return
        }
        val dialogBuilder = AlertDialog.Builder(this.context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(titleConfirm) { _, _ ->
                actionConfirm()
            }
            .setNegativeButton(titleCancel) { _, _ ->
                actionCancel()
            }
            .create()
        dialogBuilder.show()
    }

    protected fun enabledHintFragment(codeDialog: String, state: Boolean) {
        if (!CODES_HINT_DIALOG.contains(codeDialog)) {
            return
        }
        val settings = this.activity?.getSharedPreferences(SETTINGS_HINT_DIALOGS, Context.MODE_PRIVATE)
        if (settings?.getBoolean(codeDialog, !state) == state) {
            return
        }
        settings?.edit()?.putBoolean(codeDialog, state)?.apply()
    }

    companion object {
        const val SETTINGS_HINT_DIALOGS = "SETTINGS_HINT_DIALOGS"
        const val MOOD_HINT_DIALOG_ENABLED = "MOOD_HINT_DIALOG_ENABLED"
        const val SLEEP_HINT_DIALOG_ENABLED = "SLEEP_HINT_DIALOG_ENABLED"
        const val NOTE_HINT_DIALOG_ENABLED = "NOTE_HINT_DIALOG_ENABLED"
        const val PROGRESS_HINT_DIALOG_ENABLED = "PROGRESS_HINT_DIALOG_ENABLED"
        val CODES_HINT_DIALOG  = listOf<String>(
            MOOD_HINT_DIALOG_ENABLED,
            SLEEP_HINT_DIALOG_ENABLED,
            NOTE_HINT_DIALOG_ENABLED,
            PROGRESS_HINT_DIALOG_ENABLED
        )
    }
}