package com.gamaliel.advweek4160421086.view

import android.os.Bundle
import android.util.Log
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
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


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

        val studentId = arguments?.getString("studentID")
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(studentId != null){
            viewModel.fetch(studentId)
            Log.d("student_id", studentId.toString())
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it

            binding.txtID.setText(it.id)
            binding.txtName.setText(it.name)
            binding.txtBod.setText(it.bod)
            binding.txtPhone.setText(it.phone)

            val picasso = Picasso.Builder(requireContext())
            picasso.listener{
                    picasso,uri, exception->
                exception.printStackTrace()
            }

            picasso.build().load(it.photoUrl).into(binding.imageView, object:
                Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {
                    Log.e("picasso error", e.toString())
                }
            })


            binding.btnUpdate?.setOnClickListener {
                Observable.timer(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_add_24)
                    }
                val name = it.id.toString()
                binding.txtID.isEnabled = true

                Log.d("print_student", it.toString())
                binding.txtID.setText(student.id.toString())
                binding.txtName.setText(student.name)
                binding.txtBod.setText(student.bod)


            }
        })
    }
}