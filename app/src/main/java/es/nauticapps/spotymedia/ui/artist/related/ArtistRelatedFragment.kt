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
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.databinding.FragmentArtistRelatedBinding
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.ui.artist.toptracks.ArtistTopTrackViewModel
import retrofit2.HttpException
import java.net.UnknownHostException

class ArtistRelatedFragment(private val artistId: String)  : Fragment() {

    private val viewModel: ArtistRelatedViewModel by viewModels()
    lateinit var binding : FragmentArtistRelatedBinding
    lateinit var myAdapter: ArtistRelatedFramentAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentArtistRelatedBinding.inflate(inflater, container, false)

        viewModel.getState().observe(viewLifecycleOwner)  { state ->
            when(state) {
                is BaseState.Loading -> updateToLoadinglState()
                is BaseState.Normal -> updateToNormalState(state.data as ArtistRelatedListState)
                is BaseState.Error -> updateToErrorState(state.dataError)
            }
        }

        setupView()

        viewModel.requestRelated(artistId)

        return binding.root

    }

    private fun setupView() {

        myAdapter = ArtistRelatedFramentAdapter(listOf<ArtistModel>())

        val myRecyclerView : RecyclerView = binding.relatedRecyclerList
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

    }

    private fun updateToErrorState(dataError: Throwable) {

        binding.relatedProgressBar.visibility = View.GONE
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    private fun updateToNormalState(data: ArtistRelatedListState) {
        binding.relatedProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listRelated)
    }

    private fun updateToLoadinglState() {
        binding.relatedProgressBar.visibility = View.VISIBLE
    }

}