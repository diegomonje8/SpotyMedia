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

class ArtistTopTracksFragment(private val artistId: String) : Fragment() {

    private val viewModel: ArtistTopTrackViewModel by viewModels()
    lateinit var binding : FragmentArtistToptrackBinding
    lateinit var myAdapter: ArtistTopTracksFragmentAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentArtistToptrackBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner)  { state ->
            when(state) {
                is BaseState.Loading -> updateToLoadinglState()
                is BaseState.Normal -> updateToNormalState(state.data as ArtistTopTrackListState)
                is BaseState.Error -> updateToErrorState(state.dataError)
            }
        }

        setupView()

        viewModel.requestTopTracks(artistId)

        return binding.root
    }

    private fun setupView() {

        myAdapter = ArtistTopTracksFragmentAdapter(listOf<Track>())

        val myRecyclerView : RecyclerView = binding.toptrackRecyclerList
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

    }

    private fun updateToNormalState(data: ArtistTopTrackListState) {
        binding.toptrackProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listTopTracks)
    }

    private fun updateToErrorState(dataError: Throwable) {
        binding.toptrackProgressBar.visibility = View.GONE
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();
    }



    private fun updateToLoadinglState() {
        binding.toptrackProgressBar.visibility = View.VISIBLE
    }



}