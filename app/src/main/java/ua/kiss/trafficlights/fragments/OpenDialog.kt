package ua.kiss.trafficlights.fragments

import androidx.fragment.app.DialogFragment

interface OpenDialog {
    fun showDialog(dialog: DialogFragment?, tag: String)
}