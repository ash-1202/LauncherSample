package com.cnx.tesseractlauncher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val launcherItemsAdapter = LauncherItemsAdapter()
        recycler_view.adapter = launcherItemsAdapter

    }
}