package com.blagovestpetkov.roomdatabasesample

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editWordView = findViewById(R.id.edit_word)
    }

    fun saveWord(view: View) {

        val replyIntent:Intent  =  Intent();
        if (TextUtils.isEmpty(editWordView.getText())) {
            setResult(RESULT_CANCELED, replyIntent);

        } else {
            val word: String  = editWordView.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

    companion object {
        const val EXTRA_REPLY = "com.blagovestpetkov.roomdatabasesample.REPLAY"
    }
}