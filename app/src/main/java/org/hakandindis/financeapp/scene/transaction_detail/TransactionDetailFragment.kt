package org.hakandindis.financeapp.scene.transaction_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.hakandindis.financeapp.databinding.FragmentTransactionDetailBinding

class TransactionDetailFragment : Fragment() {
    private lateinit var _binding: FragmentTransactionDetailBinding
    private val binding get() = _binding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTransactionDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}