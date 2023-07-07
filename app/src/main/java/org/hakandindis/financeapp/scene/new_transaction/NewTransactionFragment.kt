package org.hakandindis.financeapp.scene.new_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.R
import org.hakandindis.financeapp.databinding.FragmentNewTransactionBinding

@AndroidEntryPoint
class NewTransactionFragment : Fragment() {
    private lateinit var _binding: FragmentNewTransactionBinding
    private val binding get() = _binding

    private val viewModel: NewTransactionViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.addTransactionButton.setOnClickListener {
            val category = when (binding.chipGroup.checkedChipId) {
                R.id.subscription_chip -> binding.subscriptionChip.text.toString()
                R.id.rent_chip -> binding.rentChip.text.toString()
                R.id.food_chip -> binding.foodChip.text.toString()
                else -> binding.clothesChip.text.toString()
            }

            viewModel.addTransaction(
                transactionName = binding.transactionTitleEditText.text.toString(),
                transactionAmount = binding.amountEditText.text.toString().toInt(),
                transactionCategory = category
            )
        }
    }
}