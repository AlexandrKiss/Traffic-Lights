package ua.kiss.trafficlights.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import ua.kiss.trafficlights.MainActivityViewModel
import ua.kiss.trafficlights.databinding.DialogInfoBinding
import ua.kiss.trafficlights.models.ColorLight

class InfoDialogFragment(
    private val colorLight: ColorLight,
    private val viewModel: MainActivityViewModel
) : DialogFragment() {
    private lateinit var binding: DialogInfoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogInfoBinding.inflate(LayoutInflater.from(context))
        binding.imgInfo.setImageResource(colorLight.imgInfo)
        binding.titleInfo.text = colorLight.name
        binding.descriptionInfo.text = colorLight.description
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setView(binding.root)
                .setPositiveButton("Понятно") { dialog, id ->
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.isVisibleDialog = false
        viewModel.onStart()
    }
}