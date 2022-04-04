package com.example.mymovice.detailfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mymovice.databinding.FragmentDetailBinding



/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentDetailBinding.inflate(inflater)
        val application= requireNotNull(activity).application
        val moveProperty=DetailFragmentArgs.fromBundle(requireArguments()).property
        val viewModelFactory=DetailViewModelFactory(moveProperty,application = application)
        val viewModel=ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.lifecycleOwner=this
        binding.detail=viewModel
        return binding.root
    }
}