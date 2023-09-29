package com.coherentsolutions.lab.contacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coherentsolutions.lab.contacts.R

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle(R.string.contacts)
        setContentView(R.layout.activity_host)
    }
}