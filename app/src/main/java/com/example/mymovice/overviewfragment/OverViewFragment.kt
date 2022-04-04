package com.example.mymovice.overviewfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mymovice.databinding.FragmentOverViewBinding
import com.example.mymovice.databinding.GridViewItemBinding

class OverViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentOverViewBinding.inflate(inflater)
        val viewModel=ViewModelProvider(this).get(OverViewViewModel::class.java)

        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        binding.moveList.adapter=PhotoGridAdapter(PhotoGridAdapter.ClickListener{
            viewModel.navigatedToDetail(it)
        })

        viewModel.navigatedToMoveDetail.observe(viewLifecycleOwner, Observer {
            if(null!=it){
                this.findNavController().navigate(OverViewFragmentDirections.actionOverViewFragmentToDetailFragment(it))
                viewModel.completeNavigation()
            }
        })

        return binding.root
    }
}