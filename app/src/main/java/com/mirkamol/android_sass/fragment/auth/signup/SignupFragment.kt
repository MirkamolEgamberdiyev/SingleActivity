package com.mirkamol.android_sass.fragment.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.android_sass.R
import com.mirkamol.android_sass.databinding.FragmentSignupBinding
import com.mirkamol.android_sass.extension.activityNavController
import com.mirkamol.android_sass.extension.navigateSafely
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment(R.layout.fragment_signup) {
    private val binding by viewBinding(FragmentSignupBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSignin.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.bSignup.setOnClickListener {
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }
    }

}