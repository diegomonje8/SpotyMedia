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
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.databinding.FragmentHomeBinding
import retrofit2.HttpException
import java.net.UnknownHostException


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewModel.getState().observe(viewLifecycleOwner)  { state ->
            when(state) {
                is BaseState.Loading -> updateToLoadinglState()
                is BaseState.Normal -> updateToNormalState(state.data as HomeListState)
                is BaseState.Error -> updateToErrorState(state.dataError)
            }
        }

        setupView()

        binding.button.setOnClickListener(View.OnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToArtistFragment("Safe ARgs Working"))
        })

        viewModel.requestAuth()
        
        return binding.root
    }

    /**
     *  Set Up View
     */
    private fun setupView() {

    }


    /**
     * Normal State Actions
     */
    private fun updateToNormalState(data: HomeListState) {

    }

    /**
     * Loading State Actions
     */
    private fun updateToLoadinglState() {

    }

    /**
     * Error State Actions
     */
    private fun updateToErrorState(dataError : Throwable) {

        val msg = when (dataError) {
            is HttpException -> "Network Error: " + dataError.code().toString()
            is UnknownHostException -> "Please, Internet connection needed !!"

            else -> "Oops Unknown Error, Please try later !!"
        }

        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();

    }




    }

