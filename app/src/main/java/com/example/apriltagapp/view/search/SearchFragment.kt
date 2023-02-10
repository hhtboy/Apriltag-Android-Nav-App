package com.example.apriltagapp.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.ListFragment
import androidx.fragment.app.strictmode.FragmentStrictMode
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apriltagapp.R
import com.example.apriltagapp.databinding.FragmentSearchBinding
import com.example.apriltagapp.model.Spot

class SearchFragment : Fragment() {

    companion object {
        const val DEFAULT_DESTINATION = "None"
    }

    private var binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()
    private var destination: String = DEFAULT_DESTINATION


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)

        val spotNames = ArrayList<String>()
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spotNames)

        viewModel.spots.observe(viewLifecycleOwner) { spots->
            spotNames.clear()
            for(spot in spots) {
                spotNames.add(spot.key)
            }
            arrayAdapter.notifyDataSetChanged()
        }


        val destSpinner = binding?.spnDest?.apply {
            adapter = arrayAdapter
            this.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    destination = spotNames[position]
                    println("목적지 : $destination")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        }

        binding?.btnStartNavi?.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToCameraFragment(destination)
            findNavController().navigate(action)
        }



        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}