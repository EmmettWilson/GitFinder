package io.github.wilson.emmett.gitfinder.search

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.github.wilson.emmett.gitfinder.githubService.SearchCommand
import io.github.wilson.emmett.gitfinder.githubService.SearchCommandFactory
import kotlinx.android.synthetic.main.activity_search.*
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
class SearchActivityTest: AutoCloseKoinTest() {

    private val searchCommandFactory = mock<SearchCommandFactory>()
    private val searchCommand = mock<SearchCommand>()

    @Before
    fun setUp() {
        StandAloneContext.loadKoinModules(listOf(module(override = true) {
            single { searchCommandFactory }
        }))
    }

    @Test
    fun searchClickedExecutesSearchAndFinishes() {
        val scenario = ActivityScenario.launch(SearchActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onActivity {

            val expected = "searchValue"
            whenever(searchCommandFactory.makeCommand(expected)).thenReturn(searchCommand)

            it.searchEditText.setText(expected)
            it.searchButton.performClick()

            verify(searchCommand).execute()

            assertTrue(it.isFinishing)
        }
    }

    @Test
    fun imeActionSearchExecutesSearchAndFinishes() {
        val scenario = ActivityScenario.launch(SearchActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onActivity {

            val expected = "searchValue"
            whenever(searchCommandFactory.makeCommand(expected)).thenReturn(searchCommand)

            it.searchEditText.setText(expected)
            it.searchEditText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)

            verify(searchCommand).execute()

            assertTrue(it.isFinishing)
        }
    }

    @Test
    fun otherImeActionsDoNothing() {
        val scenario = ActivityScenario.launch(SearchActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onActivity {

            val expected = "searchValue"
            whenever(searchCommandFactory.makeCommand(expected)).thenReturn(searchCommand)

            it.searchEditText.setText(expected)
            it.searchEditText.onEditorAction(EditorInfo.IME_ACTION_GO)
            it.searchEditText.onEditorAction(EditorInfo.IME_ACTION_NEXT)
            it.searchEditText.onEditorAction(EditorInfo.IME_ACTION_DONE)

            verifyZeroInteractions(searchCommand)
            assertFalse(it.isFinishing)
        }
    }
}