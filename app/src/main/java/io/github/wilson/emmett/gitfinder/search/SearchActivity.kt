package io.github.wilson.emmett.gitfinder.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import io.github.wilson.emmett.gitfinder.*
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.no_animation, R.anim.no_animation)
        setContentView(R.layout.activity_search)

        if (savedInstanceState == null) {
            searchConstraintLayout.onAttachedToWindow { reveal() }
        } else {
            searchConstraintLayout.visible()
        }
    }

    override fun onBackPressed() {
        finishWithAnimation()
    }

    private fun reveal() {
        val displayMetrics = measureDisplay()
        searchConstraintLayout.circularReveal(
            displayMetrics.widthPixels,
            0,
            displayMetrics.widthPixels + displayMetrics.heightPixels
        )
    }

    private fun finishWithAnimation() {
        val displayMetrics = measureDisplay()

        searchConstraintLayout.circularCollapse(
            displayMetrics.widthPixels,
            0,
            displayMetrics.widthPixels + displayMetrics.heightPixels
        ) {
            finishAfterTransition()
        }

    }

    companion object {
        fun launch(context: Activity) {
            val fakeTransition = ActivityOptionsCompat.makeSceneTransitionAnimation(context, View(context), "")
            context.startActivity(Intent(context, SearchActivity::class.java), fakeTransition.toBundle())
        }
    }
}

