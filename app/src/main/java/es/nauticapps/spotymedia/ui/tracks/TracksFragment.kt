package es.nauticapps.spotymedia.ui.tracks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.databinding.FragmentArtistRelatedBinding
import es.nauticapps.spotymedia.databinding.FragmentTracksBinding
import es.nauticapps.spotymedia.datalayer.models.AlbumTracks
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.ui.artist.ArtistFragmentArgs
import es.nauticapps.spotymedia.ui.artist.related.ArtistRelatedFramentAdapter
import es.nauticapps.spotymedia.ui.artist.related.ArtistRelatedListState
import es.nauticapps.spotymedia.ui.artist.related.ArtistRelatedViewModel
import retrofit2.HttpException
import java.net.UnknownHostException

class TracksFragment : Fragment() {

    private val viewModel: TracksViewModel by viewModels()
    lateinit var binding : FragmentTracksBinding
    lateinit var myAdapter: TracksFragmentAdapter
    val args : TracksFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {

        binding = FragmentTracksBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner)  { state ->
            when(state) {
                is BaseState.Loading -> updateToLoadinglState()
                is BaseState.Normal -> updateToNormalState(state.data as TracksListState)
                is BaseState.Error -> updateToErrorState(state.dataError)
            }
        }

        setupView()

        viewModel.requestAlbums(args.album)

        return binding.root

    }

    private fun setupView() {
        myAdapter = TracksFragmentAdapter(listOf<AlbumTracks>())

        val myRecyclerView : RecyclerView = binding.trackFragmentRecycler
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updateToNormalState(data: TracksListState) {
        binding.trackFragmentProgressBar.visibility = View.GONE
        binding.trackFragmentText.text = data.album?.name ?: ""

        var myImage = "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        try  {
            myImage = data.album?.images?.first()?.url ?: "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        } catch(e: Exception ) {  Log.e ("Spoty Media Error", "Error catching image")  }

        Picasso.get()
            .load(myImage)
            .resize(250,250)
            .centerCrop()
            .placeholder(R.drawable.ic_extraterrestre)
            .into(binding.trackFragmentImage)

        myAdapter.updateList((data).listTracks)

    }

    private fun updateToLoadinglState() {
        binding.trackFragmentProgressBar.visibility = View.VISIBLE
    }

    private fun updateToErrorState(dataError: Throwable) {
        binding.trackFragmentProgressBar.visibility = View.GONE
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"
            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}