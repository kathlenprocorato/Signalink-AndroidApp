package com.example.signalinkversiontwo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _welcomeText = MutableLiveData<String>().apply {
        value = "Welcome to Signalink!"
    }

    private val _whatToDoText = MutableLiveData<String>().apply {
        value = "What do you want to do today?"
    }

    private val _translateHeading = MutableLiveData<String>().apply {
        value = "Translate"
    }

    private val _fingerspelling = MutableLiveData<String>().apply {
        value = "Fingerspelling"
    }

    private val _phrases = MutableLiveData<String>().apply {
        value = "Phrases"
    }

    private val _learnHeading = MutableLiveData<String>().apply {
        value = "Learn"
    }

    private val _learnLetters = MutableLiveData<String>().apply {
        value = "Learn Letters"
    }

    private val _takeQuiz = MutableLiveData<String>().apply {
        value = "Take Quiz"
    }

    val text: LiveData<String> = _welcomeText
    val whatToDo: LiveData<String> = _whatToDoText
    val translateHeading: LiveData<String> = _translateHeading
    val fingerspelling: LiveData<String> = _fingerspelling
    val phrases: LiveData<String> = _phrases
    val learnHeading: LiveData<String> = _learnHeading
    val learnLetters: LiveData<String> = _learnLetters
    val takeQuiz: LiveData<String> = _takeQuiz
}