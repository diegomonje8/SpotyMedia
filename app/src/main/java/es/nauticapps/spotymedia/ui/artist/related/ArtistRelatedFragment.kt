package es.nauticapps.spotymedia.ui.artist.related

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
import es.nauticapps.spotymedia.databinding.FragmentArtistRelatedBinding
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.ui.artist.toptracks.ArtistTopTrackViewModel
import retrofit2.HttpException
import java.net.UnknownHostException

class ArtistRelatedFragment(private val artistId: String)  : BaseFragment<ArtistRelatedListState, ArtistRelatedViewModel, FragmentArtistRelatedBinding>() {

    /**
     * Base Variables
     */
    override var viewModelClass: Class<ArtistRelatedViewModel> = ArtistRelatedViewModel::class.java
    lateinit var vm : ArtistRelatedViewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArtistRelatedBinding = FragmentArtistRelatedBinding::inflate

    /**
     * Custom Variables
     */
    lateinit var myAdapter: ArtistRelatedFramentAdapter

    /**
     * Setup View
     */
    override fun setupView(viewModel: ArtistRelatedViewModel) {
        vm = viewModel
        viewModel.requestRelated(artistId)

        myAdapter = ArtistRelatedFramentAdapter(listOf<ArtistModel>())

        val myRecyclerView : RecyclerView = binding.relatedRecyclerList
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

    }

    /**
     * State Management Functions
     */
    override fun onNormal(data: ArtistRelatedListState) {
        binding.relatedProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listRelated)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.relatedProgressBar.visibility = View.VISIBLE
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