package es.nauticapps.spotymedia.ui.artist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.base.BaseExtraData
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.databinding.FragmentArtistBinding
import retrofit2.HttpException
import java.net.UnknownHostException

class ArtistFragment : Fragment() {

    private val viewModel: ArtistViewModel by viewModels()
    lateinit var binding : FragmentArtistBinding
    lateinit var pagerAdapter : ArtistViewPagerAdapter
    val args : ArtistFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentArtistBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner, { state ->
            when (state) {
                is BaseState.Normal -> onNormal(state.data as ArtistListState)
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
            }
        })

        setupView(args.artist.id)
        viewModel.getInformation(args.artist)

        return binding.root
    }

    private fun setupView(artistId: String) {

        pagerAdapter = ArtistViewPagerAdapter(this, artistId)

        binding.artistViewPager2.adapter = pagerAdapter
        TabLayoutMediator(binding.artistTabLayout, binding.artistViewPager2) { tab, position ->
            tab.text = when(position) {
                0-> "Top Tracks"
                1-> "Albums"
                2-> "Friends"
                else -> ""
            }

        }.attach()



    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }


    private fun onError(dataError: Throwable) {
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();
    }



    private fun onNormal(artistListState: ArtistListState) {
        artistListState.artist?.let { myartist ->

            binding.artTextName.text = myartist.name

            var myImage = "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
            try { myImage = myartist.images.firstOrNull()?.url.toString()
            } catch (e: Exception) {

            }
            Picasso.get()
                .load(myImage)
                .resize(120,120)
                .centerCrop()
                .placeholder(R.drawable.ic_extraterrestre)
                .into(binding.artImage)





        }

    }


}