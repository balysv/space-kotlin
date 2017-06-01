package com.balysv.gravity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.balysv.gravity.R.layout

class GravityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val parentView = window.decorView as ViewGroup
        val gravityView = LayoutInflater.from(this).inflate(layout.gravity_activity, parentView, false) as GravityView?
        setContentView(gravityView)
    }
}
