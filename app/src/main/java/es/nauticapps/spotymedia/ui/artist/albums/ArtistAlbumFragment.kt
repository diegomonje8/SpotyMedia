package es.nauticapps.spotymedia.ui.artist.albums

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
import es.nauticapps.spotymedia.databinding.FragmentArtistAlbumBinding
import es.nauticapps.spotymedia.datalayer.models.AlbumItem
import retrofit2.HttpException
import java.net.UnknownHostException

class ArtistAlbumFragment(private val artistId: String, private var listener: (Album: AlbumItem) -> Unit)  : BaseFragment<ArtistAlbumListState, ArtistAlbumViewModel, FragmentArtistAlbumBinding>() {

    /**
     * Base Vars
     */
    override var viewModelClass: Class<ArtistAlbumViewModel> = ArtistAlbumViewModel::class.java
    lateinit var vm: ArtistAlbumViewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArtistAlbumBinding = FragmentArtistAlbumBinding::inflate

    /**
     * Custom Vars
     */
    lateinit var myAdapter : ArtistAlbumFragmentAdapter

    /**
     * Setup View
     */
    override fun setupView(viewModel: ArtistAlbumViewModel) {
        vm = viewModel

        viewModel.requestAlbums(artistId)

        myAdapter = ArtistAlbumFragmentAdapter(listOf<AlbumItem>(), listener)

        val myRecyclerView : RecyclerView = binding.albumdRecyclerList
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    /**
     * State Management Functions
     */
    override fun onNormal(data: ArtistAlbumListState) {
        binding.albumProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listAlbums)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.albumProgressBar.visibility = View.VISIBLE
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