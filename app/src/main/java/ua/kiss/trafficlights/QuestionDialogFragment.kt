package ua.kiss.trafficlights

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class QuestionDialogFragment(
    private val title: String,
    private val icon: Int,
    private val viewModel: MainActivityViewModel
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            viewModel.onStop()
            viewModel.isVisibleDialog = true
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setMessage("Показать информацию о цвете?")
                .setIcon(icon)
                .setPositiveButton(android.R.string.yes) { dialog, id ->

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
        viewModel.isVisibleDialog = false
        viewModel.onStart()
    }
}