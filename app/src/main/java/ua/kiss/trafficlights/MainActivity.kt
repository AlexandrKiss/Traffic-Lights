package ua.kiss.trafficlights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import ua.kiss.trafficlights.databinding.ActivityMainBinding
import ua.kiss.trafficlights.utils.Status
import ua.kiss.trafficlights.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
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
                viewModel.onStop()
                binding.button.text = start
                setDefaultColor()
            }
        }
    }

    private fun setRed() {
        binding.rectRed.setBackgroundResource(R.drawable.rect_red)
        binding.rectRedGlow.visibility = View.VISIBLE
    }

    private fun setRedLight() {
        binding.rectRed.setBackgroundResource(R.drawable.rect_red_light)
        binding.rectRedGlow.visibility = View.GONE
    }

    private fun setYellow() {
        binding.rectYellow.setBackgroundResource(R.drawable.rect_yellow)
        binding.rectYellowGlow.visibility = View.VISIBLE
    }

    private fun setYellowLight() {
        binding.rectYellow.setBackgroundResource(R.drawable.rect_yellow_light)
        binding.rectYellowGlow.visibility = View.GONE
    }

    private fun setGreen() {
        binding.rectGreen.setBackgroundResource(R.drawable.rect_green)
        binding.rectGreenGlow.visibility = View.VISIBLE
    }

    private fun setGreenLight() {
        binding.rectGreen.setBackgroundResource(R.drawable.rect_green_light)
        binding.rectGreenGlow.visibility = View.GONE
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders
            .of(this, ViewModelFactory())
            .get(MainActivityViewModel::class.java)
    }
}