package io.github.wilson.emmett.gitfinder

import android.view.View.*
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.whenever
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.test.AutoCloseKoinTest
import org.koin.test.declareMock

@RunWith(AndroidJUnit4::class)
class MainActivityTest : AutoCloseKoinTest() {

    private val gitRepoViewModel = declareMock<GitRepoViewModel>()

    @Before
    fun setUp() {
        loadKoinModules(listOf(module(override = true) {
            viewModel { gitRepoViewModel }
        }))
        whenever(gitRepoViewModel.getErrorState()).thenReturn(MutableLiveData())
    }

    @Test
    fun emptyViewIsShownWhenNoRepositories() {
        val mutableLiveData = MutableLiveData<List<GitRepo>>()
        whenever(gitRepoViewModel.gitRepositories).thenReturn(mutableLiveData)

        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onActivity {

            onView(withId(R.id.gitRepoRecycler)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
            onView(withId(R.id.emptyView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

            mutableLiveData.postValue(listOf(GitRepo.test(), GitRepo.test(), GitRepo.test()))

            onView(withId(R.id.gitRepoRecycler)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            onView(withId(R.id.emptyView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
            onView(withId(R.id.emptyViewInstructions)).check(matches(withText(R.string.emptyTextInstructions)))
            onView(withId(R.id.emptyViewMessage)).check(matches(withText(R.string.emptyTextMessage)))
        }
    }

    @Test
    fun repositoriesListShownWhenViewModelReturnsRepositories() {
        val mutableLiveData = MutableLiveData<List<GitRepo>>()
        whenever(gitRepoViewModel.gitRepositories).thenReturn(mutableLiveData)

        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onActivity {

            val expected = GitRepo.test()
            mutableLiveData.postValue(listOf(GitRepo.test(), GitRepo.test(), expected))

            assertEquals(GONE, it.emptyView.visibility)
            assertEquals(VISIBLE, it.gitRepoRecycler.visibility)

            assertRecyclerHasCount(R.id.gitRepoRecycler, 3)
            assertRepoAtPosition(expected, 2)
        }
    }

    private fun assertRecyclerHasCount(@IdRes id: Int, expected: Int) {
        onView(withId(id)).check(matches(withRecyclerViewCount(expected)))
    }

    private fun assertRepoAtPosition(repo: GitRepo, position: Int) {
        onView(withRecyclerView(R.id.gitRepoRecycler).atPositionOnView(position, R.id.repoName))
            .check(matches(withText(repo.full_name)))
    }

    private fun withRecyclerView(@IdRes id: Int): RecyclerViewMatcher = RecyclerViewMatcher(id)
}