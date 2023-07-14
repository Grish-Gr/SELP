package com.mter.selp.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mter.selp.R
import com.mter.selp.data.network.request.Sex
import com.mter.selp.databinding.FragmentRegisterBinding
import com.mter.selp.viewmodels.AuthViewModel
import java.text.SimpleDateFormat
import java.util.Date

class RegistrationFragment : BaseFragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val dataModel : DataModel by activityViewModels()
    private val viewModel by viewModels<AuthViewModel>()

    private var birthdateTime: Long = Date().time

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
        observe()
    }

    private fun observe() {
        viewModel.registration.observe(this.viewLifecycleOwner) {
            Toast.makeText(
                this.context,
                "Подтвердите регистрацию. На ${it.email} пришла ссылка с подтверждением",
                Toast.LENGTH_LONG
            ).show()
            binding.progressRegistration.visibility = View.INVISIBLE
            openFragment(LoginFragment())
        }
    }

    fun initAction(){
        binding.create.setOnClickListener {
            viewModel.registration(
                email = binding.registerEmail.editableText.toString(),
                password = binding.registerPassword.editableText.toString(),
                sex = when(binding.sexChoice.editText.toString()){
                    "Мужской" -> Sex.MAN
                    "Женский" -> Sex.WOMAN
                    else      -> Sex.MAN
                },
                birthdateTime = birthdateTime
            )
            binding.progressRegistration.visibility = View.VISIBLE
        }
        binding.changeBirthdate.setOnClickListener {
            openDialogBirthdate()
        }
        (binding.sexChoice.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }

    private fun openDialogBirthdate(){
        val picker = MaterialDatePicker
            .Builder
            .datePicker()
            .setTitleText("Выберите дату рождения")
            .build()
        picker.addOnPositiveButtonClickListener {
            birthdateTime = it
            binding.birthdate.text = DateFormat.format("dd.MM.yyyy", Date(it)).toString();
        }
        picker.show(parentFragmentManager, null)
    }

    private companion object {
        val items = arrayOf("Мужской", "Женский")
    }
}
