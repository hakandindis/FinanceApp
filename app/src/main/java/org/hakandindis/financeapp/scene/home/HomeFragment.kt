package org.hakandindis.financeapp.scene.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.databinding.FragmentHomeBinding
import org.hakandindis.financeapp.scene.model.TransactionModel


@AndroidEntryPoint
class HomeFragment : Fragment(), TransactionClickListener {
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var adapter: RecentTransactionsAdapter
    private val binding get() = _binding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        adapter = RecentTransactionsAdapter(this)
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
            if (list?.isNotEmpty() == true) {
                binding.noTransactionText.isVisible = false
                adapter.submitList(list)
            } else {
                binding.noTransactionText.isVisible = true
            }
        }
        viewModel.totalBalance.observe(viewLifecycleOwner) {
            binding.fragmentHomeTotalBalanceValue.text = "$it TL"
        }
        viewModel.totalIncome.observe(viewLifecycleOwner) {
            binding.fragmentHomeIncomeValue.text = "$it TL"
        }
        viewModel.totalExpense.observe(viewLifecycleOwner) {
            binding.fragmentHomeExpenseValue.text = "$it TL"
        }
    }

    override fun onTransactionShortClick(model: TransactionModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToTransactionDetailFragment(model)
        findNavController().navigate(action)
    }
}