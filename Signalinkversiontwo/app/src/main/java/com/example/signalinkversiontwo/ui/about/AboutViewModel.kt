package com.example.signalinkversiontwo.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Signalink is an Android application that translates sign language into text. Signalink aims to promote inclusivity for the deaf and hard of hearing, making it possible for them to communicate towards the hearing with ease."
    }
    val text: LiveData<String> = _text
}