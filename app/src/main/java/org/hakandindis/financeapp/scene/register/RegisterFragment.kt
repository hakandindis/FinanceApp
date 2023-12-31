package org.hakandindis.financeapp.scene.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.databinding.FragmentRegisterBinding
import org.hakandindis.financeapp.scene.main.MainActivity
import org.hakandindis.financeapp.scene.main.MainActivityViewModel
import org.hakandindis.financeapp.util.AuthStates

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var _binding: FragmentRegisterBinding
    private val binding get() = _binding

    private val viewModel: RegisterViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        observeAllLiveData()
    }

    private fun initializeListeners() {
        binding.fragmentRegisterButton.setOnClickListener {
            val email = binding.fragmentRegisterEmailEditText.text.toString()
            val password = binding.fragmentRegisterPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.registerWithEmailAndPassword(email = email, password = password)
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Giriş Hatası")
                    .setMessage("Lütfen mail adresini doğru formatta giriniz ve şifrenizi en az 6 karakter olacak şekilde seçiniz")
                    .show()
            }
        }
        binding.loginText.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeAllLiveData() {
        activityViewModel.isAuthenticated.observe(viewLifecycleOwner) { isAuthenticated ->
            if (isAuthenticated) {
                val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }
        viewModel.authState.observe(viewLifecycleOwner) {
            when (it) {
                AuthStates.INITIAL -> {}
                AuthStates.LOADING -> {}
                AuthStates.SUCCESS -> {
                    activityViewModel.isAuthenticated.value = true
                    val isRemember = binding.rememberMeCheckBox.isChecked
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe
                    with(sharedPref.edit()) {
                        putBoolean(MainActivity.IS_REMEMBER, isRemember)
                        apply()
                    }
                }

                AuthStates.FAILED -> {}
            }
        }
    }
}