package com.example.waterme.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.waterme.databinding.FragmentLogoBinding
import com.example.waterme.utils.UiUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogoFragment : Fragment() {
    private var binding: FragmentLogoBinding? = null
    private val bind get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            findNavController().navigate(LogoFragmentDirections.actionLogoFragmentToHomeFragment())
        binding = FragmentLogoBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UiUtils.setStatusBarTransparent(requireActivity(), bind.root, true)

        lifecycleScope.launch {
            delay(888)
            findNavController().navigate(LogoFragmentDirections.actionLogoFragmentToHomeFragment())
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}