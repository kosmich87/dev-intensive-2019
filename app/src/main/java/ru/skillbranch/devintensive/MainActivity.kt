package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender
import android.view.inputmethod.EditorInfo
import ru.skillbranch.devintensive.extensions.hideKeyboard


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage: ImageView
    lateinit var textTV: TextView
    lateinit var messageET: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTV = tv_question
        messageET = et_message
        sendBtn = iv_send

        sendBtn.setOnClickListener(this)

        val status = savedInstanceState?.getString("Status") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("Question") ?: Bender.Question.NAME.name
        benderObj = Bender(status = Bender.Status.valueOf(status), question = Bender.Question.valueOf(question))

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTV.text = benderObj.askQuestion()

        messageET.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendAnswer()
                true
            }
            false}

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Status", benderObj.status.name)
        outState.putString("Question", benderObj.question.name)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send){
            sendAnswer()
        }
    }

    fun sendAnswer(){
        val (phase, color) = benderObj.listenAnswer(messageET.text.toString().toLowerCase())
        messageET.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTV.text = phase
        hideKeyboard()
    }
}