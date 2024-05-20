package com.example.signalinkversiontwo.ui.translate

import android.annotation.SuppressLint
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.signalinkversiontwo.databinding.ItemGestureRecognizerResultBinding
import com.google.mediapipe.tasks.components.containers.Category
import java.util.Locale
import kotlin.math.min

class PhrasesRecognizerResultsAdapter(private val context: Context) :
    RecyclerView.Adapter<PhrasesRecognizerResultsAdapter.ViewHolder>(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val language = tts?.setLanguage(Locale.ENGLISH)
            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
                Log.i("TTS", "TextToSpeech Initialized Successfully!")
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    companion object {
        private const val NO_VALUE = "No hands detected."
    }

    private var adapterCategories: MutableList<Category?> = mutableListOf()
    private var adapterSize: Int = 0

    private val words = mutableListOf<String?>()
    private var sentence = ""

    @SuppressLint("NotifyDataSetChanged")
    fun updateResults(categories: List<Category>?) {
        adapterCategories = MutableList(adapterSize) { null }
        if (categories != null) {
            val sortedCategories = categories.sortedByDescending { it.score() }
            val min = min(sortedCategories.size, adapterCategories.size)
            for (i in 0 until min) {
                adapterCategories[i] = sortedCategories[i]
            }
            adapterCategories.sortedBy { it?.index() }
            notifyDataSetChanged()
        }
    }

    fun updateAdapterSize(size: Int) {
        adapterSize = size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemGestureRecognizerResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapterCategories[position]?.let { category ->
            holder.bind(category.categoryName(), category.score())
        }
    }

    override fun getItemCount(): Int = adapterCategories.size

    inner class ViewHolder(private val binding: ItemGestureRecognizerResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(label: String?, score: Float?) {
            with(binding) {
                if (label == null && words.isNullOrEmpty()) {
                    tvLabel.text = NO_VALUE
                }

                if(words.isNullOrEmpty()){
                    words.add("")
                }

                if ((words.last() != label && label != null && score.toString().toDouble() >= 0.70)) {
                    if (label.equals("Delete", ignoreCase = true)){
                        words.removeLast()
                    } else {
                        words.add(label)
                    }
                }
                sentence = words.joinToString(separator = " ") { it ?: "" }
                tvLabel.text = sentence

                //subject to change cause it looops
                if (tts != null && !tts!!.isSpeaking) {
                    tts?.speak(sentence, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }
    }
}
