package io.github.wilson.emmett.gitfinder.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import io.github.wilson.emmett.gitfinder.*
import io.github.wilson.emmett.gitfinder.githubService.SearchCommandFactory
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject


class SearchActivity : AppCompatActivity() {
    private val searchCommandFactory: SearchCommandFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.no_animation, R.anim.no_animation)
        setContentView(R.layout.activity_search)

        if (savedInstanceState == null) {
            searchConstraintLayout.onAttachedToWindow { reveal() }
        } else {
            searchConstraintLayout.visible()
        }

        searchEditText.setOnEditorActionListener(
            TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch()
                    return@OnEditorActionListener true
                }
                false
            })

        searchButton.setOnClickListener { performSearch() }
    }

    private fun performSearch() {
        val queryString = searchEditText.text.toString()
        searchCommandFactory.makeCommand(queryString).execute()
        searchEditText.hideKeyboard()
        finishWithAnimation()
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
        ){
            searchEditText.showKeyboard()
        }
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
