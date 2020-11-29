package lab.chevalier.braintrainer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import lab.chevalier.braintrainer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val listAnswer = mutableListOf<Int>()
    private var correctAnswer = -1

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
            this.binding.btnStart -> this.start(
                this.binding.btnStart,
                this.binding.gridContainer,
                this.binding.tvQuestion,
                this.binding.btnChoice1,
                this.binding.btnChoice2,
                this.binding.btnChoice3,
                this.binding.btnChoice4
            )
        }
    }

    private fun start(
        button: Button,
        gridLayout: GridLayout,
        textView: TextView,
        buttonChoice1: Button,
        buttonChoice2: Button,
        buttonChoice3: Button,
        buttonChoice4: Button
    ) {
        button.visibility = View.GONE
        gridLayout.visibility = View.VISIBLE
        this.generateQuestion(textView)
        this.generateAnswer(buttonChoice1, buttonChoice2, buttonChoice3, buttonChoice4)
    }

    private fun generateAnswer(
        buttonChoice1: Button,
        buttonChoice2: Button,
        buttonChoice3: Button,
        buttonChoice4: Button
    ) {
        val position = this.generateRandomInt(4)
        this.listAnswer.clear()
        for (x in 0..3) {
            if (position == x) this.listAnswer.add(this.correctAnswer)
            else {
                var randomAnswer = this.generateRandomInt(41)
                while (randomAnswer == (this.correctAnswer)) {
                    randomAnswer = this.generateRandomInt(41)
                }
                this.listAnswer.add(randomAnswer)
            }
        }

        buttonChoice1.text = this.listAnswer[0].toString()
        buttonChoice2.text = this.listAnswer[1].toString()
        buttonChoice3.text = this.listAnswer[2].toString()
        buttonChoice4.text = this.listAnswer[3].toString()
    }

    private fun generateQuestion(textView: TextView) {
        val a = this.generateRandomInt(21)
        val b = this.generateRandomInt(21)
        this.correctAnswer = a + b
        textView.text = "$a + $b"
    }

    private fun generateRandomInt(bound: Int): Int {
        return Random.nextInt(bound)
    }
}