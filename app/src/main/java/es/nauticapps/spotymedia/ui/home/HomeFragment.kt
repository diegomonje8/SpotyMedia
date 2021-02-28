package es.nauticapps.spotymedia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.nauticapps.spotymedia.base.BaseExtraData
import es.nauticapps.spotymedia.base.BaseFragment
import es.nauticapps.spotymedia.base.hideKeyboard
import es.nauticapps.spotymedia.databinding.FragmentHomeBinding
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.datalayer.models.FeaturesItem
import es.nauticapps.spotymedia.datalayer.models.RealeaseItem
import retrofit2.HttpException
import java.net.UnknownHostException


class HomeFragment() : BaseFragment<HomeListState, HomeViewModel, FragmentHomeBinding>() {

    /**
     * Base Variables
     */
    override var viewModelClass = HomeViewModel::class.java
    lateinit var vm: HomeViewModel
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding  = FragmentHomeBinding::inflate

    /**
     * Custom Variables
     */
    lateinit var myAdapter: HomeFragmentAdapter
    lateinit var myAdapterRelease: HomeFragmentAdapterRealeses
    lateinit var myAdapterFeature: HomeFragmentAdapterFeatures

    /**
     * Setup View
     */
    override fun setupView(viewModel: HomeViewModel) {

        vm = viewModel
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
            vm.requestArtist(searchedText)
            hideKeyboard()
        })
    }

    /**
     * State Management Functions
     */
    override fun onNormal(data: HomeListState) {
        binding.fragmentHomeProgressBar.visibility = View.GONE
        myAdapter.updateList((data).listArtists)
        myAdapterRelease.updateList((data).listRelease)
        myAdapterFeature.updateList((data).listFeatures)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.fragmentHomeProgressBar.visibility = View.VISIBLE
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

