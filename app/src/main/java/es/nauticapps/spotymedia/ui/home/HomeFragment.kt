package es.nauticapps.spotymedia.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.base.hideKeyboard
import es.nauticapps.spotymedia.databinding.FragmentHomeBinding
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.datalayer.models.FeaturesItem
import es.nauticapps.spotymedia.datalayer.models.RealeaseItem
import retrofit2.HttpException
import java.net.UnknownHostException


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding
    lateinit var myAdapter: HomeFragmentAdapter
    lateinit var myAdapterRelease: HomeFragmentAdapterRealeses
    lateinit var myAdapterFeature: HomeFragmentAdapterFeatures

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewModel.getState().observe(viewLifecycleOwner)  { state ->
            when(state) {
                is BaseState.Loading -> updateToLoadinglState()
                is BaseState.Normal -> updateToNormalState(state.data as HomeListState)
                is BaseState.Error -> updateToErrorState(state.dataError)
            }
        }

        setupView()

        viewModel.requestArtist("jackson")
        
        return binding.root
    }

    /**
     *  Set Up View
     *

     *
     */
    private fun setupView() {

        myAdapter = HomeFragmentAdapter(listOf<ArtistModel>()) { artist ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToArtistFragment(artist))
        }

        myAdapterRelease = HomeFragmentAdapterRealeses(listOf<RealeaseItem>())
        myAdapterFeature = HomeFragmentAdapterFeatures(listOf<FeaturesItem>())

        val myRecyclerViewFeature : RecyclerView = binding.homeFragmentrecyclerFeature
        myRecyclerViewFeature.apply {
            adapter = myAdapterFeature
            layoutManager =  LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }


        val myRecyclerViewRelease : RecyclerView = binding.homeFragmentrecyclerViewRealease
        myRecyclerViewRelease.apply {
            adapter = myAdapterRelease
            layoutManager =  LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }

        val myRecyclerView : RecyclerView = binding.myRecyclerList
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        binding.outlinedTextField.setEndIconOnClickListener(View.OnClickListener {
            val searchedText = binding.outlinedTextField.editText?.text.toString()
            viewModel.requestArtist(searchedText)
            hideKeyboard()
        })

    }


    /**
     * Normal State Actions
     */
    private fun updateToNormalState(data: HomeListState) {
        binding.fragmentHomeProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listArtists)
        myAdapterRelease.updateList((data).listRelease)
        myAdapterFeature.updateList((data).listFeatures)
    }

    /**
     * Loading State Actions
     */
    private fun updateToLoadinglState() {
        binding.fragmentHomeProgressBar.visibility = View.VISIBLE
    }

    /**
     * Error State Actions
     */
    private fun updateToErrorState(dataError : Throwable) {
        binding.fragmentHomeProgressBar.visibility = View.GONE
        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }

        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();

    }




    }

