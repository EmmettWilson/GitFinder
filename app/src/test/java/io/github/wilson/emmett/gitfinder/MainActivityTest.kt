package io.github.wilson.emmett.gitfinder

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
import org.mockito.Mockito.`when`
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest : AutoCloseKoinTest() {

    private val gitRepoViewModel = declareMock<GitRepoViewModel>()

    @Before
    fun setUp() {
        loadKoinModules(listOf(module(override = true) {
            viewModel { gitRepoViewModel }
        }))
    }

    @Test
    fun textSetFromViewModel() {
        val expectedText = "hello view-model"
        `when`(gitRepoViewModel.getText()).thenReturn(expectedText)

        val activity = Robolectric.setupActivity(MainActivity::class.java)

        assertEquals(expectedText, activity.mainText.text)
    }
}