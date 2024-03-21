package com.gamaliel.advweek4160421086.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gamaliel.advweek4160421086.R
import com.gamaliel.advweek4160421086.databinding.FragmentStudentDetailBinding
import com.gamaliel.advweek4160421086.viewmodel.DetailViewModel
import com.gamaliel.advweek4160421086.viewmodel.ListViewModel


class StudentDetailFragment : Fragment() {

    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            binding.txtID.setText(student.id ?: "")
            binding.txtName.setText(student.name ?: "")
            binding.txtBod.setText(student.bod ?: "")
            binding.txtPhone.setText(student.phone ?: "")
        })
    }


}