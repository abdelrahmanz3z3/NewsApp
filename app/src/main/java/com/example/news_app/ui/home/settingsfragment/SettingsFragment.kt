package com.example.news_app.ui.home.settingsfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.news_app.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    lateinit var viewBinding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSettingsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences =
            requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        viewBinding.spinnerMode.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 1) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    } else if (position == 2) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    editor.putInt("posMode", position)
                    editor.apply()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        viewBinding.spinnerMode.setSelection(sharedPreferences.getInt("posMode", 1))



        viewBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    val appLocale = LocaleListCompat.forLanguageTags("en")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                } else if (position == 2) {
                    val appLocale = LocaleListCompat.forLanguageTags("ar")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
                editor.putInt("pos", position)
                editor.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        viewBinding.spinner.setSelection(sharedPreferences.getInt("pos", 1))

    }


}