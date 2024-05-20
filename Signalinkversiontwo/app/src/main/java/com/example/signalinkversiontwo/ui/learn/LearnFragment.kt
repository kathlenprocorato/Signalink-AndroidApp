package com.example.signalinkversiontwo.ui.learn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.signalinkversiontwo.learnactivity.LettersActivity
import com.example.signalinkversiontwo.learnactivity.QuizActivity
import com.example.signalinkversiontwo.learnactivity.SimplePhrasesActivity
import com.example.signalinkversiontwo.databinding.FragmentLearnBinding

class LearnFragment : Fragment() {

    private var _binding: FragmentLearnBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val learnViewModel =
            ViewModelProvider(this).get(LearnViewModel::class.java)

        _binding = FragmentLearnBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get references to the LinearLayouts
        val lettersButton: LinearLayout = binding.lettersButton
        val simplePhrasesButton: LinearLayout = binding.simplePhrasesButton
        val quizButton: LinearLayout = binding.takeQuizButton

        // Set OnClickListener for the Letters Button
        lettersButton.setOnClickListener {
            // Start LettersActivity
            startActivity(Intent(requireContext(), LettersActivity::class.java))
        }

        // Set OnClickListener for the Simple Phrases Button
        simplePhrasesButton.setOnClickListener {
            // Start SimplePhrasesActivity
            startActivity(Intent(requireContext(), SimplePhrasesActivity::class.java))
        }

        // Set OnClickListener for the Quiz Button
        quizButton.setOnClickListener {
            // Start QuizActivity
            startActivity(Intent(requireContext(), QuizActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}