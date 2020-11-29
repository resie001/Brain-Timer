package lab.chevalier.braintrainer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import lab.chevalier.braintrainer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(this.binding) {
            this.btnChoice1.setOnClickListener(this@MainActivity)
            this.btnChoice2.setOnClickListener(this@MainActivity)
            this.btnChoice3.setOnClickListener(this@MainActivity)
            this.btnChoice4.setOnClickListener(this@MainActivity)
            this.btnStart.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            this.binding.btnStart -> this.start(p0 as Button, this.binding.gridContainer)
        }
    }

    private fun start(button: Button, gridLayout: GridLayout) {
        button.visibility = View.GONE
        gridLayout.visibility = View.VISIBLE
    }
}