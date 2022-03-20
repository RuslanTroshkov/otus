package ru.ruslan.otustest

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import android.widget.TextView
import ru.ruslan.otustest.databinding.ActivityFullscreenBinding

class FullscreenActivity : AppCompatActivity() {

	private lateinit var binding: ActivityFullscreenBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityFullscreenBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}