package com.example.waterme.ui.c_u_d

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.waterme.BaseApplication
import com.example.waterme.R
import com.example.waterme.core.model.Plant
import com.example.waterme.databinding.FragmentCreateUpdateDeleteBinding
import com.example.waterme.ui.viewmodel.ViewModelFactory

class CreateUpdateDeleteFragment : Fragment() {
    private var binding: FragmentCreateUpdateDeleteBinding? = null
    private val bind get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateUpdateDeleteBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(bind.toolbarCUD.root)
            title =
                if (args.isInsert) getText(R.string.insert) else getString(R.string.update, "...")
        }
        return bind.root
    }

    private val args: CreateUpdateDeleteFragmentArgs by navArgs()
    private val viewModel: CreateUpdateDeleteViewModel by viewModels {
        ViewModelFactory(requireActivity().application as BaseApplication)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showContent(false)
        if (!args.isInsert) {
            viewModel.getPlant(args.id).observe(viewLifecycleOwner) { plantNull ->
                plantNull?.also { plant ->
                    (requireActivity() as AppCompatActivity).title =
                        getString(R.string.update, plant.name)
                    bind.plantNameET.setText(plant.name)
                    bind.plantTypeET.setText(plant.type)
                    bind.plantDescriptionET.setText(plant.description)
                    when {
                        plant.schedule.equals("daily", true) -> bind.plantScheduleDaily.isChecked =
                            true
                        plant.schedule.equals(
                            "weekly",
                            true
                        ) -> bind.plantScheduleWeekly.isChecked =
                            true
                        plant.schedule.equals(
                            "monthly",
                            true
                        ) -> bind.plantScheduleMonthly.isChecked =
                            true
                    }
                    showContent(true)

                    bind.deleteButton.setOnClickListener {
                        val dialog = AlertDialog.Builder(requireContext()).apply {
                            setMessage(getString(R.string.delete, plant.name))
                            setPositiveButton(getText(R.string.yes)) { dialog, _ ->
                                viewModel.deletePlant(plant)
                                dialog.dismiss()
                                findNavController().navigateUp()
                            }
                            setNegativeButton(getText(R.string.no)) { dialog, _ -> dialog.dismiss() }
                        }.create()
                        dialog.show()
                    }
                }
            }
        } else showContent(true)

        bind.doneButton.setOnClickListener {
            val etS = arrayOf(bind.plantNameET, bind.plantTypeET, bind.plantDescriptionET)
            var isEmpty = false
            for (et in etS) {
                if (et.text.isNullOrBlank()) {
                    et.error = getText(R.string.must_fill)
                    isEmpty = true
                    break
                }
            }
            if (!isEmpty) {
                val schedule =
                    view.findViewById<RadioButton>(bind.plantSchedule.checkedRadioButtonId).text
                val plant =
                    Plant(
                        if (args.isInsert) 0 else args.id, bind.plantNameET.text.toString(),
                        schedule.toString(), bind.plantTypeET.text.toString(),
                        bind.plantDescriptionET.text.toString()
                    )
                if (args.isInsert) viewModel.insertPlant(plant)
                else viewModel.updatePlant(plant)
                findNavController().navigateUp()
            }
        }
    }

    private fun showContent(isDone: Boolean) {
        val contentVisibility = if (isDone) View.VISIBLE else View.GONE
        bind.contentCUD.visibility = contentVisibility
        bind.deleteButton.visibility = if (args.isInsert) View.GONE else contentVisibility
        bind.loadingCUD.visibility = if (isDone) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}