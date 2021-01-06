package ua.kiss.trafficlights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    fun setClick() {
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

    private fun showDialog(title: String, icon: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage("Показать информацию о цвете?")
        builder.setIcon(icon)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    private fun setRed() {
        val color = R.drawable.rect_red
        binding.rectRed.setBackgroundResource(color)
        binding.rectRedGlow.visibility = View.VISIBLE
        binding.rectRed.setOnClickListener {
            showDialog("Красный", color)
        }
    }

    private fun setRedLight() {
        binding.rectRed.setBackgroundResource(R.drawable.rect_red_light)
        binding.rectRedGlow.visibility = View.GONE
        binding.rectRed.setOnClickListener(null)
    }

    private fun setYellow() {
        val color = R.drawable.rect_yellow
        binding.rectYellow.setBackgroundResource(color)
        binding.rectYellowGlow.visibility = View.VISIBLE
        binding.rectYellow.setOnClickListener {
            showDialog("Желтый", color)
        }
    }

    private fun setYellowLight() {
        binding.rectYellow.setBackgroundResource(R.drawable.rect_yellow_light)
        binding.rectYellowGlow.visibility = View.GONE
        binding.rectYellow.setOnClickListener(null)
    }

    private fun setGreen() {
        val color = R.drawable.rect_green
        binding.rectGreen.setBackgroundResource(color)
        binding.rectGreenGlow.visibility = View.VISIBLE
        binding.rectGreen.setOnClickListener {
            showDialog("Зеленый", color)
        }
    }

    private fun setGreenLight() {
        binding.rectGreen.setBackgroundResource(R.drawable.rect_green_light)
        binding.rectGreenGlow.visibility = View.GONE
        binding.rectGreen.setOnClickListener(null)
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders
            .of(this, ViewModelFactory())
            .get(MainActivityViewModel::class.java)
    }
}