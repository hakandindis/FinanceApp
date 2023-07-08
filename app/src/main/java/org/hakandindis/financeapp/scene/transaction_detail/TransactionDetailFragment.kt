package org.hakandindis.financeapp.scene.transaction_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.databinding.FragmentTransactionDetailBinding

@AndroidEntryPoint
class TransactionDetailFragment : Fragment() {
    private lateinit var _binding: FragmentTransactionDetailBinding
    private val binding get() = _binding
    private val viewModel: TransactionDetailViewModel by viewModels()
    private val args by navArgs<TransactionDetailFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTransactionDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = args.model
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.deleteFab.setOnClickListener {
            viewModel.deleteTransaction(args.model)
            findNavController().popBackStack()
        }
    }
}