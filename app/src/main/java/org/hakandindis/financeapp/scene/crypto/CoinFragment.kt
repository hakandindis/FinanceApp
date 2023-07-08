package org.hakandindis.financeapp.scene.crypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.databinding.FragmentCoinBinding

@AndroidEntryPoint
class CoinFragment : Fragment(), CoinClickListener {
    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CoinViewModel by viewModels()
    private lateinit var adapter: CoinAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCoinBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initializeListeners()
        initializeObservers()
    }

    private fun initializeViews() {
        adapter = CoinAdapter(this)
        binding.fragmentCoinList.adapter = adapter
    }

    private fun initializeListeners() {

    }

    private fun initializeObservers() {
        viewModel.coins.observe(viewLifecycleOwner) { list ->
            if (list?.isNotEmpty() == true) {
                adapter.submitList(list)
            }
        }
    }

    override fun onCoinShortClick() {

    }

}