package com.gamaliel.advweek4160421086.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamaliel.advweek4160421086.R
import com.gamaliel.advweek4160421086.databinding.FragmentGameListBinding
import com.gamaliel.advweek4160421086.databinding.FragmentStudentListBinding
import com.gamaliel.advweek4160421086.viewmodel.GameViewModel
import com.gamaliel.advweek4160421086.viewmodel.ListViewModel


class GameListFragment : Fragment() {
    private lateinit var binding: FragmentGameListBinding
    private lateinit var viewModel: GameViewModel
    private val gameListAdapter = GameListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.refresh()

        binding.recViewGame.layoutManager = LinearLayoutManager(context)
        binding.recViewGame.adapter = gameListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recViewGame.visibility = View.GONE

            binding.refreshLayout.isRefreshing = false
        }

    }
    fun observeViewModel() {
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
            gameListAdapter.updateGameList(it)
        })

        viewModel.gameLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.gameLoadLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recViewGame.visibility = View.GONE
            } else {
                binding.progressLoad.visibility = View.GONE
                binding.recViewGame.visibility = View.VISIBLE
            }
        })
    }
}