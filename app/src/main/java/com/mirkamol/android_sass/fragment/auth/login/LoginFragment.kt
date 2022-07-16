package com.mirkamol.android_sass.fragment.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.android_sass.R
import com.mirkamol.android_sass.databinding.FragmentLoginBinding
import com.mirkamol.android_sass.databinding.FragmentSignupBinding
import com.mirkamol.android_sass.extension.activityNavController
import com.mirkamol.android_sass.extension.navigateSafely
import com.mirkamol.android_sass.manager.AuthManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.bSignin.setOnClickListener {
            AuthManager.isAuthorized = true

            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }

        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }
}