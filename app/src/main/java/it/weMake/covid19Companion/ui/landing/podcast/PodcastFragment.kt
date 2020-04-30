package it.weMake.covid19Companion.ui.landing.podcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.weMake.covid19Companion.R

class PodcastFragment : Fragment() {

    private lateinit var pocastViewModel: PocastViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        pocastViewModel =
                ViewModelProvider(this).get(PocastViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_podcast, container, false)
        return root
    }
}
