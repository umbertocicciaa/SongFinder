package com.main.songfinder.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.main.songfinder.R

class UndoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_undo)

        val resultIntent = Intent()
        val undoButton : Button = findViewById(R.id.undoButton)
        val constraintLayout :ConstraintLayout= findViewById(R.id.constraint_layout)
        undoButton.setOnClickListener {
            resultIntent.putExtra("undo_result", 0)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        constraintLayout.setOnClickListener {
            resultIntent.putExtra("undo_result", -1)
            setResult(Activity.RESULT_CANCELED, resultIntent)
            finish()
        }

    }
}