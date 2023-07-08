package org.hakandindis.financeapp.scene.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.R
import org.hakandindis.financeapp.databinding.FragmentLoginBinding
import org.hakandindis.financeapp.extension.showEmailOrPasswordNotValidToast
import org.hakandindis.financeapp.scene.main.MainActivity
import org.hakandindis.financeapp.util.AuthStates


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var _binding: FragmentLoginBinding
    private val binding get() = _binding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        observeAllLiveData()
    }

    private fun initializeListeners() {
        binding.fragmentLoginButton.setOnClickListener {
            val email = binding.fragmentLoginEmailEditText.text.toString()
            val password = binding.fragmentLoginPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginWithEmailAndPassword(email = email, password = password)
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Giriş Hatası")
                    .setMessage("Lütfen mail adresini doğru formatta giriniz ve şifrenizi en az 6 karakter olacak şekilde seçiniz")
                    .show()
            }
        }
        binding.registerText.setOnClickListener {
            findNavController().navigate(R.id.action_global_mainFragment)
        }
    }

    private fun observeAllLiveData() {
        viewModel.authState.observe(viewLifecycleOwner) {
            when (it) {
                AuthStates.INITIAL -> {}
                AuthStates.LOADING -> {}
                AuthStates.SUCCESS -> {
                    val isRemember = binding.rememberMeCheckBox.isChecked
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe
                    with(sharedPref.edit()) {
                        putBoolean(MainActivity.IS_REMEMBER, isRemember)
                        apply()
                    }
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(action)
                }

                AuthStates.FAILED -> {
                    context?.showEmailOrPasswordNotValidToast()
                }
            }
        }
    }
}