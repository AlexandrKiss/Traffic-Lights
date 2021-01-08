package ua.kiss.trafficlights

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import ua.kiss.trafficlights.databinding.ActivityMainBinding
import ua.kiss.trafficlights.fragments.InterfaceHolder
import ua.kiss.trafficlights.fragments.OpenDialog
import ua.kiss.trafficlights.fragments.QuestionDialogFragment
import ua.kiss.trafficlights.models.greenLight
import ua.kiss.trafficlights.models.redLight
import ua.kiss.trafficlights.models.yellowLight
import ua.kiss.trafficlights.utils.Status
import ua.kiss.trafficlights.utils.ViewModelFactory

class MainActivity : AppCompatActivity(), OpenDialog {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    private val start = "Start"
    private val stop = "Stop"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
        setDefaultColor()
        InterfaceHolder.set(this)
        setClick()
    }

    private fun setDefaultColor() {
        setRedLight()
        setYellowLight()
        setGreenLight()
    }

    private fun setClick() {
        binding.button.setOnClickListener {
            if (binding.button.text == start) {
                setDefaultColor()
                binding.button.text = stop
                viewModel.updateStatus.observe(this, {
                    if (it == Status.STOP) {
                        setGreenLight()
                        setRed()
                    }
                    if (it == Status.ATTENTION) {
                        setYellow()
                    }
                    if (it == Status.START) {
                        setRedLight()
                        setYellowLight()
                        setGreen()
                    }
                })
                viewModel.onStart()
            } else {
                viewModel.onStop(true)
                binding.button.text = start
                setDefaultColor()
            }
        }
    }

    private fun setRed() {
        binding.rectRedGlow.visibility = View.VISIBLE
        binding.rectRedGlow.setOnClickListener {
            showDialog(QuestionDialogFragment(redLight, viewModel),
                "QuestionDialogRed")
        }
    }

    private fun setRedLight() {
        binding.rectRedGlow.visibility = View.INVISIBLE
        binding.rectRedGlow.setOnClickListener(null)
    }

    private fun setYellow() {
        binding.rectYellowGlow.visibility = View.VISIBLE
        binding.rectYellowGlow.setOnClickListener {
            showDialog(QuestionDialogFragment(yellowLight, viewModel),
                "QuestionDialogYellow")
        }
    }

    private fun setYellowLight() {
        binding.rectYellowGlow.visibility = View.INVISIBLE
        binding.rectYellowGlow.setOnClickListener(null)
    }

    private fun setGreen() {
        binding.rectGreenGlow.visibility = View.VISIBLE
        binding.rectGreenGlow.setOnClickListener {
            showDialog(QuestionDialogFragment(greenLight, viewModel),
                "QuestionDialogGreen")
        }
    }

    private fun setGreenLight() {
        binding.rectGreenGlow.visibility = View.INVISIBLE
        binding.rectGreenGlow.setOnClickListener(null)
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders
            .of(this, ViewModelFactory())
            .get(MainActivityViewModel::class.java)
    }

    override fun showDialog(dialog: DialogFragment?, tag: String) {
        dialog?.show(supportFragmentManager, tag);
    }
}