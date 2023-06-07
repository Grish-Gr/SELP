package com.mter.selp.ui.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mter.selp.R
import com.mter.selp.databinding.FragmentMainBinding

class MainFragment: BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    var openReminder = false
    private var openSleepHint = true
    private var openMoodHint = true
    private var openProgressHint = true
    private var openNoteHint = true

    private val dialog = ReminderFragment()
    private val sleepHint = SleepHintFragment()
    private val moodHint = MoodHintFragment()
    private val progressHint = ProgressHintFragment()
    private val noteHint = NoteHintFragment()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    override fun onStart() {
        super.onStart()
        if (!openReminder){
            dialog.show(parentFragmentManager, null)
            openReminder = true
        }

        val settings = this.activity?.getSharedPreferences(SETTINGS_APP, Context.MODE_PRIVATE)
        if (settings?.getBoolean(HELP_WITH_SOUND, true) == true){
            binding.optionHelpBreathSound.backgroundTintList = context?.getColorStateList(R.color.md_theme_light_colorSecondaryVariant)
            binding.optionHelpBreathVideo.backgroundTintList =
                ColorStateList.valueOf(Color.WHITE)
        } else {
            binding.optionHelpBreathVideo.backgroundTintList = context?.getColorStateList(R.color.md_theme_light_colorSecondaryVariant)
            binding.optionHelpBreathSound.backgroundTintList =
                ColorStateList.valueOf(Color.WHITE)
        }
    }

    private fun initAction(){
        binding.helpCard.setOnClickListener {
            val settings = this.activity?.getSharedPreferences(SETTINGS_APP, Context.MODE_PRIVATE)
            if (settings?.getBoolean(HELP_WITH_SOUND, true) == true){
                openFragment(BreathHelpVolumeFragment())
            } else {
                openFragment(BreathHelpVideoFragment())
            }
        }
        binding.optionHelpBreathSound.setOnClickListener {
            it.backgroundTintList = context?.getColorStateList(R.color.md_theme_light_colorSecondaryVariant)
            binding.optionHelpBreathVideo.backgroundTintList =
                ColorStateList.valueOf(Color.WHITE)
            val settings = this.activity?.getSharedPreferences(SETTINGS_APP, Context.MODE_PRIVATE)
            settings?.edit()?.putBoolean(HELP_WITH_SOUND, true)?.apply()
        }
        binding.optionHelpBreathVideo.setOnClickListener {
            it.backgroundTintList = context?.getColorStateList(R.color.md_theme_light_colorSecondaryVariant)
            binding.optionHelpBreathSound.backgroundTintList =
                ColorStateList.valueOf(Color.WHITE)
            val settings = this.activity?.getSharedPreferences(SETTINGS_APP, Context.MODE_PRIVATE)
            settings?.edit()?.putBoolean(HELP_WITH_SOUND, false)?.apply()
        }
        binding.exercisesProgessive.setOnClickListener {
            openFragment(MeditationFragment())
            if (openProgressHint) {
                progressHint.show(parentFragmentManager, null)
                openProgressHint = false
            }
        }
        binding.myMood.setOnClickListener {
            openFragment(MoodFragment())
            if (openMoodHint) {
                moodHint.show(parentFragmentManager, null)
                openMoodHint = false
            }
        }
        binding.account.setOnClickListener{
            openFragment(AccountFragment())
        }
        binding.sleep.setOnClickListener {
            openFragment(SleepAnalyzedFragment())
            if (openSleepHint) {
                sleepHint.show(parentFragmentManager, null)
                openSleepHint = false
            }
        }
        binding.notes.setOnClickListener{
            openFragment(InformationFragment())
            if (openNoteHint) {
                noteHint.show(parentFragmentManager, null)
                openNoteHint = false
            }
        }
    }

    private companion object {
        const val SETTINGS_APP = "SettingsApp"
        const val HELP_WITH_SOUND = "SettingOptionHelpBreath"
    }
}
