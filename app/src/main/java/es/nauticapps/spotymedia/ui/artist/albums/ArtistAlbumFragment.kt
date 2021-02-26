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
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.databinding.FragmentArtistAlbumBinding
import es.nauticapps.spotymedia.datalayer.models.AlbumItem
import retrofit2.HttpException
import java.net.UnknownHostException

class ArtistAlbumFragment(private val artistId: String)  : Fragment() {

    private val viewModel: ArtistAlbumViewModel by viewModels()
    lateinit var binding : FragmentArtistAlbumBinding
    lateinit var myAdapter : ArtistAlbumFragmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentArtistAlbumBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            when(state) {
                is BaseState.Loading -> updateToLoadingState()
                is BaseState.Normal -> updayeToNormalState(state.data as ArtistAlbumListState)
                is BaseState.Error -> updateToErrorState(state.dataError)
            }
        }

        setUpView()

        viewModel.requestAlbums(artistId)

        return binding.root
    }

    private fun setUpView() {
        myAdapter = ArtistAlbumFragmentAdapter(listOf<AlbumItem>())

        val myRecyclerView : RecyclerView = binding.albumdRecyclerList
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun updayeToNormalState(data: ArtistAlbumListState) {
        binding.albumProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listAlbums)
    }

    private fun updateToLoadingState() {
        binding.albumProgressBar.visibility = View.VISIBLE

    }

    private fun updateToErrorState(dataError: Throwable) {
        binding.albumProgressBar.visibility = View.GONE
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
}