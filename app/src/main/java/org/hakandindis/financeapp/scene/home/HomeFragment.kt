package org.hakandindis.financeapp.scene.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.databinding.FragmentHomeBinding


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var adapter: RecentTransactionsAdapter
    private val binding get() = _binding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initializeListeners()
        observeAllLiveData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRecentTransactions()
    }

    private fun initializeViews() {
        adapter = RecentTransactionsAdapter()
        binding.fragmentHomeRecentTransactionList.adapter = adapter
    }

    private fun initializeListeners() {
        binding.fragmentHomeFab.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNewTransactionFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeAllLiveData() {
        viewModel.recentTransactions.observe(viewLifecycleOwner) { list ->
            Log.d("XATIRO", list?.size.toString())
            list?.let { adapter.submitList(it) }
        }
    }
}