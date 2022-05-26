package com.example.waterme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterme.BaseApplication
import com.example.waterme.R
import com.example.waterme.databinding.FragmentHomeBinding
import com.example.waterme.ui.ReminderDialogFragment
import com.example.waterme.ui.viewmodel.ViewModelFactory
import com.example.waterme.utils.UiUtils

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val bind get() = binding!!

    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BaseApplication))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        UiUtils.setStatusBarTransparent(requireActivity(), bind.root, false)
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(bind.toolbarHome.root)
            title = getText(R.string.home)
        }
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeAdapter({  // longClickListener
            val dialog = ReminderDialogFragment(it.name)
            dialog.show(this@HomeFragment.childFragmentManager, "WaterMeDialogSet")
        }) { navigateToCUD(it.id, false) /* editClick */ }
        bind.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
            setHasFixedSize(false)
        }

        bind.addPlantButton.setOnClickListener { navigateToCUD(it.id) }

        viewModel.getAllPlants().observe(viewLifecycleOwner) {
            val list = it.filterNotNull()
            adapter.submitList(list)
        }
    }

    private fun navigateToCUD(id: Int, isInsert: Boolean = true) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id, isInsert)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}