package es.nauticapps.spotymedia.ui.artist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import es.nauticapps.spotymedia.databinding.FragmentArtistBinding

class ArtistFragment : Fragment() {

    lateinit var binding : FragmentArtistBinding
    val args : ArtistFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentArtistBinding.inflate(inflater, container, false)

        Log.e("ITEM", args.test)

        return binding.root
    }


}