package edu.uw.rgoyal17.dotify.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uw.rgoyal17.dotify.BuildConfig
import edu.uw.rgoyal17.dotify.R
import edu.uw.rgoyal17.dotify.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAboutBinding.inflate(inflater)

        binding.tvVersion.text = binding.root.context.getString(R.string.version, BuildConfig.VERSION_NAME)

        return binding.root
    }

}