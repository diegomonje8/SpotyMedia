package es.nauticapps.spotymedia.ui.artist.toptracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.nauticapps.spotymedia.base.BaseExtraData
import es.nauticapps.spotymedia.base.BaseFragment
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.databinding.FragmentArtistBinding
import es.nauticapps.spotymedia.databinding.FragmentArtistToptrackBinding
import es.nauticapps.spotymedia.datalayer.models.Track
import es.nauticapps.spotymedia.ui.artist.ArtistFragmentArgs
import es.nauticapps.spotymedia.ui.artist.ArtistListState
import es.nauticapps.spotymedia.ui.artist.ArtistViewModel
import es.nauticapps.spotymedia.ui.home.HomeFragmentAdapter
import es.nauticapps.spotymedia.ui.home.HomeListState
import retrofit2.HttpException
import java.net.UnknownHostException

class ArtistTopTracksFragment(private val artistId: String) : BaseFragment<ArtistTopTrackListState, ArtistTopTrackViewModel, FragmentArtistToptrackBinding>() {


    /**
     * Base vars
     */
    override var viewModelClass: Class<ArtistTopTrackViewModel> = ArtistTopTrackViewModel::class.java
    lateinit var vm : ArtistTopTrackViewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArtistToptrackBinding = FragmentArtistToptrackBinding::inflate


    /**
     * Custom Vars
     */
    lateinit var myAdapter: ArtistTopTracksFragmentAdapter

    /**
     * SetUp View
     */
    override fun setupView(viewModel: ArtistTopTrackViewModel) {
        vm = viewModel
        viewModel.requestTopTracks(artistId)

        myAdapter = ArtistTopTracksFragmentAdapter(listOf<Track>())

        val myRecyclerView : RecyclerView = binding.toptrackRecyclerList
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    /**
     * Manage States
     */
    override fun onNormal(data: ArtistTopTrackListState) {
        binding.toptrackProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listTopTracks)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.toptrackProgressBar.visibility = View.VISIBLE
    }

    override fun onError(dataError: Throwable) {
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();
    }

}