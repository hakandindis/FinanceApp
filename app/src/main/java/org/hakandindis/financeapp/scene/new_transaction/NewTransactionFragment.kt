package org.hakandindis.financeapp.scene.new_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.R
import org.hakandindis.financeapp.databinding.FragmentNewTransactionBinding
import org.hakandindis.financeapp.util.TransactionCategories
import org.hakandindis.financeapp.util.TransactionTypes

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
        initializeViews()
        initializeListeners()
    }

    private fun initializeViews() {
        val typeArray = arrayOf(TransactionTypes.INCOME.value, TransactionTypes.EXPENSE.value)
        (binding.transactionTypeTextView as MaterialAutoCompleteTextView).setSimpleItems(typeArray)

        val categoryArray = arrayOf(
            TransactionCategories.FUN.value,
            TransactionCategories.FOOD.value,
            TransactionCategories.SUBSCRIPTION.value,
            TransactionCategories.TRANSPORTATION.value
        )
        (binding.transactionCategoryTextView as MaterialAutoCompleteTextView).setSimpleItems(categoryArray)
    }

    private fun initializeListeners() {
        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransaction(
                transactionName = binding.transactionTitleEditText.text.toString(),
                transactionAmount = binding.amountEditText.text.toString().toInt(),
                transactionType = binding.transactionTypeTextView.text.toString(),
                transactionCategory = binding.transactionCategoryTextView.text.toString(),
                )

            findNavController().navigate(R.id.action_global_home_fragment)
        }
    }
}