package com.bara.recapitulation.ui.Recap.RecapUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.databinding.FragmentUserRecapBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserViewModel


class RecapUserFragment : Fragment() {
    private lateinit var recapViewModel: RecapUserViewModel
    private var _binding: FragmentUserRecapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recapViewModel = ViewModelProvider(this).get(RecapUserViewModel::class.java)
        _binding = FragmentUserRecapBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData(){
        recapViewModel.getDate.observe(viewLifecycleOwner, Observer {
            binding.txtThisMonth.text = it
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}