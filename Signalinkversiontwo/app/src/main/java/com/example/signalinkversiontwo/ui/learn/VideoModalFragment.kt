import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.DialogFragment
import com.example.signalinkversiontwo.R

class VideoModalFragment : DialogFragment() {
    private var videoView: VideoView? = null
    private var videoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoUri = it.getParcelable(ARG_VIDEO_URI)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_modal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoView = view.findViewById(R.id.videoView)
        videoUri?.let {
            videoView?.setVideoURI(it)
            videoView?.start()
        }
    }

    companion object {
        private const val ARG_VIDEO_URI = "videoUri"
        fun newInstance(videoUri: Uri?): VideoModalFragment {
            val fragment = VideoModalFragment()
            val args = Bundle().apply {
                putParcelable(ARG_VIDEO_URI, videoUri)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
