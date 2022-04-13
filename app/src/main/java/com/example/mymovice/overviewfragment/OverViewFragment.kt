package com.example.mymovice.overviewfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mymovice.databinding.FragmentOverViewBinding

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
        if(!viewModel.result ){
            binding.progressBar.visibility=View.VISIBLE
            Toast.makeText(activity,"No Internet",Toast.LENGTH_SHORT).show()
        }else if(viewModel.result){
            binding.progressBar.visibility=View.GONE
            Toast.makeText(activity,"This App Need Internet",Toast.LENGTH_SHORT).show()
        }
        else{
            binding.progressBar.visibility=View.VISIBLE
        }

        return binding.root
    }
}