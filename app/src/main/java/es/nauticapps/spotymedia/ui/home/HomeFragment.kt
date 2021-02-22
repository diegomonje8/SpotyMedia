package es.nauticapps.spotymedia.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)


        binding.button.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_artistFragment)
        })

        return binding.root
    }

}