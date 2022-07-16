package com.mirkamol.android_sass.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mirkamol.android_sass.R
import com.mirkamol.android_sass.manager.AuthManager


class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.myLooper()!!).postDelayed({
            when{
                AuthManager.isAuthorized ->{
                    findNavController().navigate(R.id.action_splashFragment3_to_mainFlowFragment)
                }

                !AuthManager.isAuthorized ->{
                    findNavController().navigate(R.id.action_splashFragment3_to_loginFlowFragment)
                }
            }
        },1000)

    }

}