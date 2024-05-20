package com.example.signalinkversiontwo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.signalinkversiontwo.LettersActivity
import com.example.signalinkversiontwo.R
import com.example.signalinkversiontwo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set up button click listener
        binding.fingerspellingToText.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_permissionsFragment)
        }

        val textView: TextView = binding.textWelcome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val textWhatToDoView: TextView = binding.textWhatToDo
        homeViewModel.whatToDo.observe(viewLifecycleOwner) {
            textWhatToDoView.text = it
        }

        val textTranslateHeadingView: TextView = binding.textTranslateHeading
        homeViewModel.translateHeading.observe(viewLifecycleOwner) {
            textTranslateHeadingView.text = it
        }

        val textFingerspelling: TextView = binding.fingerspelling
        homeViewModel.fingerspelling.observe(viewLifecycleOwner) {
            textFingerspelling.text = it
        }

        val textPhrases: TextView = binding.phrases
        homeViewModel.phrases.observe(viewLifecycleOwner) {
            textPhrases.text = it
        }

        val textLearnHeadingView: TextView = binding.textLearnHeading
        homeViewModel.learnHeading.observe(viewLifecycleOwner) {
            textLearnHeadingView.text = it
        }

        val textLearnLetters: TextView = binding.learnLettersText
        homeViewModel.learnLetters.observe(viewLifecycleOwner) {
            textLearnLetters.text = it
        }

        val textTakeQuiz: TextView = binding.takeQuizzesText
        homeViewModel.takeQuiz.observe(viewLifecycleOwner) {
            textTakeQuiz.text = it
        }

        val lettersButton: LinearLayout = binding.learnLetters

        lettersButton.setOnClickListener {
            // Start LettersActivity
            startActivity(Intent(requireContext(), LettersActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}