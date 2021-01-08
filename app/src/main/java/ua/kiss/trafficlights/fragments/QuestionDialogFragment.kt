package ua.kiss.trafficlights.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ua.kiss.trafficlights.MainActivityViewModel
import ua.kiss.trafficlights.models.ColorLight

class QuestionDialogFragment(
    private val colorLight: ColorLight,
    private val viewModel: MainActivityViewModel
) : DialogFragment() {
    private var flag: Boolean = true
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            viewModel.onStop()
            viewModel.isVisibleDialog = true
            val builder = AlertDialog.Builder(it)
            builder.setTitle(colorLight.name)
                .setMessage("Показать информацию о цвете?")
                .setIcon(colorLight.img)
                .setPositiveButton(android.R.string.yes) { dialog, id ->
                    flag = false
                    InterfaceHolder.get()
                        ?.showDialog(InfoDialogFragment(colorLight, viewModel), "yes")
                }
                .setNegativeButton(android.R.string.no) { dialog, id ->
                    viewModel.isVisibleDialog = false
                    viewModel.onStart()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (flag) {
            viewModel.isVisibleDialog = false
            viewModel.onStart()
        }
    }
}