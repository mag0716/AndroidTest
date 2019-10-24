package com.github.mag0716.androidtest

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModelTestTarget by viewModels()
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text)
        viewModel.data.observe(this, Observer {
            textView.text = "Loaded : $it"
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData(1)
    }
}
