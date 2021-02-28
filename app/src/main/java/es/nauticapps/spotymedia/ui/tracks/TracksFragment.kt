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
import es.nauticapps.spotymedia.base.BaseExtraData
import es.nauticapps.spotymedia.base.BaseFragment
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

class TracksFragment : BaseFragment<TracksListState, TracksViewModel, FragmentTracksBinding>() {

    /**
     * Base Vars
     */
    override var viewModelClass: Class<TracksViewModel> = TracksViewModel::class.java
    lateinit var vm : TracksViewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTracksBinding = FragmentTracksBinding::inflate


    /**
     * Custom Vars
     */
    lateinit var myAdapter: TracksFragmentAdapter
    val args : TracksFragmentArgs by navArgs()

    /**
     * SetUp View
     */
    override fun setupView(viewModel: TracksViewModel) {
        vm = viewModel
        viewModel.requestAlbums(args.album)

        myAdapter = TracksFragmentAdapter(listOf<AlbumTracks>())

        val myRecyclerView : RecyclerView = binding.trackFragmentRecycler
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }


    /**
     * Manage States
     */
    override fun onNormal(data: TracksListState) {
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

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.trackFragmentProgressBar.visibility = View.VISIBLE
    }

    override fun onError(dataError: Throwable) {
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"
            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }


}