package com.mter.selp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.mter.selp.R
import com.mter.selp.databinding.FragmentAccountBinding
import com.mter.selp.viewmodels.AccountViewModel
import com.mter.selp.viewmodels.DataModel

class AccountFragment : BaseFragment() {

    private lateinit var binding: FragmentAccountBinding

    private val dataModel : DataModel by activityViewModels()
    private val viewModel by viewModels<AccountViewModel>()

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

        binding.account.setOnClickListener {
            openFragment(PremiumFragment())
        }

        binding.admin.setOnClickListener {
            showGetPermissionAdminDialog()
        }
    }

    private fun showGetPermissionAdminDialog() {
        val li = LayoutInflater.from(context);
        val dialogView = li.inflate(R.layout.fragment_get_admin_perm_dialog, null);

        val mDialogBuilder = AlertDialog.Builder(context);

        mDialogBuilder.setView(dialogView);

        val userInput = dialogView.findViewById<EditText>(R.id.input_text);

        mDialogBuilder
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                run {
                    this.viewModel.getPermissionAdmin(userInput.editableText.toString())
                }
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                run {
                    dialog.cancel();
                }
            }

        val alertDialog = mDialogBuilder.create();
        alertDialog.show();

    }
}
