package es.nauticapps.spotymedia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(R.layout.activity_main)
    }
}