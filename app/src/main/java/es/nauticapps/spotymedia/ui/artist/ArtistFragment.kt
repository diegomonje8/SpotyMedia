package es.nauticapps.spotymedia.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.base.BaseExtraData
import es.nauticapps.spotymedia.base.BaseFragment
import es.nauticapps.spotymedia.databinding.FragmentArtistBinding
import retrofit2.HttpException
import java.net.UnknownHostException

class ArtistFragment : BaseFragment<ArtistListState, ArtistViewModel, FragmentArtistBinding>() {

    /**
     * Base Variables
     */

    override var viewModelClass = ArtistViewModel::class.java
    lateinit var vm : ArtistViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArtistBinding = FragmentArtistBinding::inflate

    /**
     * Custom Variables
     */

    lateinit var pagerAdapter : ArtistViewPagerAdapter
    val args : ArtistFragmentArgs by navArgs()


    /**
     * SetUp View
     */

    override fun setupView(viewModel: ArtistViewModel) {
        vm = viewModel

        vm.getInformation(args.artist)

        pagerAdapter = ArtistViewPagerAdapter(this, args.artist.id) { album ->
            findNavController().navigate(ArtistFragmentDirections.actionArtistFragmentToTracksFragment(album))
        }

        binding.artistViewPager2.adapter = pagerAdapter
        TabLayoutMediator(binding.artistTabLayout, binding.artistViewPager2) { tab, position ->
            tab.text = when(position) {
                0-> "Top Tracks"
                1-> "Albums"
                2-> "Related Artists"
                else -> ""
            }

        }.attach()
    }

    /**
     * State Management
     */

    override fun onNormal(data: ArtistListState) {
        data.artist?.let { myartist ->

            binding.artTextName.text = myartist.name
            binding.artistFragmentGenres.text = getAllGenres(myartist.genres)
            binding.artistFragmentFollowersText.text = myartist.followers.total.toString()
            binding.artistFragmentRankingText.text = myartist.popularity.toString()

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

    override fun onLoading(dataLoading: BaseExtraData?) { }

    override fun onError(dataError: Throwable) {
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();
    }




    /**
     * Custom Tools
     */

    private fun getAllGenres(artists: List<String>) : String {
        var result = ""
        artists.forEach {
            result = if (result.isEmpty()) it
            else "$result, $it"
        }
        return result
    }




}