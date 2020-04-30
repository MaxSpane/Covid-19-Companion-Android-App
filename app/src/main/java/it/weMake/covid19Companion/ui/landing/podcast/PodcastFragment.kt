package it.weMake.covid19Companion.ui.landing.podcast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentPodcastBinding
import it.weMake.covid19Companion.utils.*


class PodcastFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: PodcastViewModel
    private lateinit var binding: FragmentPodcastBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PodcastViewModel::class.java)
        binding = FragmentPodcastBinding.inflate(inflater, container, false)

        binding.facebookIV.setOnClickListener(this)
        binding.twitterIV.setOnClickListener(this)
        binding.spotifyIV.setOnClickListener(this)
        binding.applePodcastsIV.setOnClickListener(this)
        binding.googlePodcasts.setOnClickListener(this)
        binding.youtubeIV.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.facebookIV -> openLink(PUBLIC_HEALTH_ON_CALL_FACEBOOK_PAGE_URL)

            R.id.twitterIV -> openLink(PUBLIC_HEALTH_ON_CALL_TWITTER_HANDLE_URL)

            R.id.spotifyIV -> openLink(PUBLIC_HEALTH_ON_CALL_SPOTIFY_URL)

            R.id.applePodcastsIV -> openLink(PUBLIC_HEALTH_ON_CALL_APPLE_PODCASTS_URL)

            R.id.googlePodcasts -> openLink(PUBLIC_HEALTH_ON_CALL_GOOGLE_PODCASTS_URL)

            R.id.youtubeIV -> openLink(PUBLIC_HEALTH_ON_CALL_YOUTUBE_CHANNEL_URL)

        }
    }

    private fun openLink(url: String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}
