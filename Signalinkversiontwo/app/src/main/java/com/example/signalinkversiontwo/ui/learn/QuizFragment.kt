package com.example.signalinkversiontwo.ui.learn

import androidx.fragment.app.Fragment
import com.example.signalinkversiontwo.GestureRecognizerHelper

class QuizFragment : Fragment(),
    GestureRecognizerHelper.GestureRecognizerListener {


    override fun onError(error: String, errorCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {
        TODO("Not yet implemented")
    }
}