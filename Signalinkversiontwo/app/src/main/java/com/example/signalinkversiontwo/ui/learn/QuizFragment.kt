package com.example.signalinkversiontwo.ui.learn

<<<<<<< HEAD
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.signalinkversiontwo.GestureRecognizerHelper
import com.example.signalinkversiontwo.R
import com.example.signalinkversiontwo.OverlayView
import com.google.mediapipe.tasks.vision.core.RunningMode
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class QuizFragment : Fragment(), GestureRecognizerHelper.GestureRecognizerListener {

    private lateinit var viewFinder: PreviewView
    private lateinit var overlayView: OverlayView
    private lateinit var quizText: TextView
    private lateinit var quizAnswer: EditText
    private lateinit var passButton: Button

    private lateinit var gestureRecognizerHelper: GestureRecognizerHelper

    private lateinit var backgroundExecutor: ExecutorService

    private var currentQuestion: String = ""
    private var questionCount = 0
    private val maxQuestions = 5
    private var passClicked = false
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        viewFinder = view.findViewById(R.id.view_finder)
        overlayView = view.findViewById(R.id.overlay)
        quizText = view.findViewById(R.id.quiz_text)
        quizAnswer = view.findViewById(R.id.quiz_answer)
        passButton = view.findViewById(R.id.btnPass)

        // Initialize our background executor
        backgroundExecutor = Executors.newSingleThreadExecutor()

        // Wait until the view is laid out before setting up the camera
        viewFinder.post {
            setupCamera()
            startQuiz()
        }
        passButton.setOnClickListener {
            passClicked = true
        }

        return view
    }

    private fun setupCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(viewFinder.surfaceProvider)
            }

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            val imageAnalyzer = ImageAnalysis.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .setTargetRotation(viewFinder.display.rotation)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .build()
                .also {
                    it.setAnalyzer(backgroundExecutor) { imageProxy ->
                        recognizeHand(imageProxy)
                    }
                }

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
                Log.d("QuizFragment", "Camera bound to lifecycle")
            } catch (exc: Exception) {
                Log.e("QuizFragment", "Camera initialization failed", exc)
                Toast.makeText(requireContext(), "Camera initialization failed: ${exc.message}", Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(requireContext()))

        // Ensure context is still valid when creating the GestureRecognizerHelper
        requireContext().let { context ->
            gestureRecognizerHelper = GestureRecognizerHelper(
                context = context,
                runningMode = RunningMode.LIVE_STREAM,
                gestureRecognizerListener = this
            )
        }
    }

    private fun startQuiz() {
        questionCount = 0
        nextQuestion()
    }

    private fun nextQuestion() {
        if (questionCount >= maxQuestions) {
            quizText.text = "Quiz Completed! You got $score correct answers."
            quizAnswer.setText("")
            return
        }
        currentQuestion = getRandomLetter()
        quizText.text = "Show the letter: $currentQuestion"
        questionCount++
    }

    private fun getRandomLetter(): String {
        val letters = ('A'..'Z')
        return letters.random().toString()
    }

    private fun recognizeHand(imageProxy: ImageProxy) {
        try {
            val mirroredImage = true // Assuming front camera is used
            gestureRecognizerHelper.recognizeLiveStream(imageProxy, mirroredImage)
        } catch (e: Exception) {
        } finally {
            imageProxy.close()
        }
    }

    private var attemptCount = 3

    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {
        val recognizedGesture = resultBundle.results.firstOrNull()?.gestures()?.firstOrNull()?.toString()
        val categoryName = extractCategoryName(recognizedGesture)
        Log.d("GestureRecognition", "Recognized Gesture: $categoryName")

        requireActivity().runOnUiThread {
            if (passClicked) {
                if (categoryName != null && !categoryName.isEmpty()) {
                    if (categoryName.equals(currentQuestion, ignoreCase = true)) {
                        // Display "Correct: <letter>" in the quizAnswer view
                        quizAnswer.setText("Correct: $currentQuestion")
                        nextQuestion() // Move to the next question
                        score++
                        attemptCount = 3 // Reset attempt count for the next question
                    } else {
                        attemptCount--
                        quizAnswer.setText("Wrong! Try again: $attemptCount")

                        if (attemptCount == 0) {
                            // Display "Wrong" message after 3 attempts
                            quizAnswer.setText("Wrong")
                            nextQuestion() // Move to the next question
                            attemptCount = 3 // Reset attempt count for the next question
                        }
                    }
                }

                passClicked = false

            }
        }

        // Update the overlay view with the results
        overlayView.setResults(resultBundle.results.first(), resultBundle.inputImageHeight, resultBundle.inputImageWidth, RunningMode.LIVE_STREAM)
        overlayView.invalidate()
    }



    private fun extractCategoryName(gestureString: String?): String {
        // Example input: "[<Category "L" (displayName= score=0.9998733 index=-1)>]"
        // Extracting the category name "L" from the input string
        return gestureString?.substringAfter('"')?.substringBefore('"') ?: ""
    }

    override fun onError(error: String, errorCode: Int) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        gestureRecognizerHelper.clearGestureRecognizer()
        backgroundExecutor.shutdown()
    }
}
=======
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
>>>>>>> 2243e2e1d5060d319043e4890a120f5a7aa7475a
