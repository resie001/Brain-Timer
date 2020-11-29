package lab.chevalier.braintrainer

import android.os.Bundle
import android.os.CountDownTimer
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
    private var totalQuestion = 0
    private var totalCorrectQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(this.binding) {
            this.btnChoice1.setOnClickListener(this@MainActivity)
            this.btnChoice2.setOnClickListener(this@MainActivity)
            this.btnChoice3.setOnClickListener(this@MainActivity)
            this.btnChoice4.setOnClickListener(this@MainActivity)
            this.btnStart.setOnClickListener(this@MainActivity)
            this.tvTotalQuestions.text =
                "${this@MainActivity.totalCorrectQuestion}/${this@MainActivity.totalQuestion}"
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            this.binding.btnStart -> this.start(
                this.binding.btnStart,
                this.binding.gridContainer,
                this.binding.tvTimer,
                this.binding.tvResult
            )
            this.binding.btnChoice1 -> this.checkAnswer(
                this.binding.btnChoice1.text.toString().toInt(),
                this.binding.tvResult,
                this.binding.tvTotalQuestions
            )
            this.binding.btnChoice2 -> this.checkAnswer(
                this.binding.btnChoice2.text.toString().toInt(),
                this.binding.tvResult,
                this.binding.tvTotalQuestions
            )
            this.binding.btnChoice3 -> this.checkAnswer(
                this.binding.btnChoice3.text.toString().toInt(),
                this.binding.tvResult,
                this.binding.tvTotalQuestions
            )
            this.binding.btnChoice4 -> this.checkAnswer(
                this.binding.btnChoice4.text.toString().toInt(),
                this.binding.tvResult,
                this.binding.tvTotalQuestions
            )
        }
    }

    private fun start(
        button: Button,
        gridLayout: GridLayout,
        timer: TextView,
        result: TextView
    ) {
        button.visibility = View.GONE
        gridLayout.visibility = View.VISIBLE
        this.generateQuestion(this.binding.tvQuestion)
        this.generateAnswer(
            this.binding.btnChoice1,
            this.binding.btnChoice2,
            this.binding.btnChoice3,
            this.binding.btnChoice4
        )
        val countdown = object : CountDownTimer(30100, 1000) {
            override fun onTick(p0: Long) {
                timer.text = "${p0 / 1000}s"
            }

            override fun onFinish() {
                gridLayout.visibility = View.GONE
                result.visibility = View.GONE
                this@MainActivity.generateResult(
                    this@MainActivity.binding.tvResultTotal,
                    this@MainActivity.binding.tvResultCorrect,
                    this@MainActivity.binding.tvResultWrong
                )
            }
        }
        countdown.start()
    }

    private fun checkAnswer(answer: Int, result: TextView, totalQuestions: TextView) {
        if (this.correctAnswer == answer) {
            with(result) {
                this.text = "Benar"
                this.setTextColor(resources.getColor(R.color.green))
            }
            this.totalCorrectQuestion++
        } else {
            with(result) {
                this.text = "Salah"
                this.setTextColor(resources.getColor(R.color.red))
            }
        }
        this.totalQuestion++
        totalQuestions.text =
            "${this@MainActivity.totalCorrectQuestion} / ${this@MainActivity.totalQuestion}"
        this.generateQuestion(this.binding.tvQuestion)
        this.generateAnswer(
            this.binding.btnChoice1,
            this.binding.btnChoice2,
            this.binding.btnChoice3,
            this.binding.btnChoice4
        )
    }

    private fun generateResult(total: TextView, correct: TextView, wrong: TextView) {
        with(total) {
            this.visibility = View.VISIBLE
            this.text = "Total: ${this@MainActivity.totalQuestion.toString()}"
        }
        with(correct) {
            this.visibility = View.VISIBLE
            this.text = "Benar: ${this@MainActivity.totalCorrectQuestion.toString()}"
        }
        with(wrong) {
            this.visibility = View.VISIBLE
            this.text =
                "Salah: ${this@MainActivity.totalQuestion - this@MainActivity.totalCorrectQuestion}"
        }
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