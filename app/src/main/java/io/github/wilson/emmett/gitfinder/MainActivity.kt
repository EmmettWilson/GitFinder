package io.github.wilson.emmett.gitfinder

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.githubService.SearchCommandFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val gitRepoViewModel: GitRepoViewModel by viewModel()
    private val searchCommandFactory : SearchCommandFactory by inject()
    private val gitRepositoryRecyclerAdapter = GitRepositoryRecyclerAdapter()
    private var layoutManagerSavedState: Parcelable? = null
    private val errorSnackBar : Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gitRepoRecycler.adapter = gitRepositoryRecyclerAdapter
        gitRepoRecycler.layoutManager = LinearLayoutManager(this)

        gitRepoViewModel.getRepositories().observe(this, Observer{
            if(it.isEmpty()){
                showEmptyView()
            }else {
                showRepos(it)
            }
        })

        gitRepoViewModel.getErrorState().observe(this, Observer {
            showError(it)
        })

        searchButton.setOnClickListener {
            errorSnackBar?.dismiss()
            searchCommandFactory.makeCommand(searchText.text.toString()).execute()
        }
    }

    private fun showError(errorState: ErrorState) {
        Snackbar.make(activity_main_constraint, errorState.message, Snackbar.LENGTH_INDEFINITE).apply {
            setActionTextColor(Color.WHITE)
            setAction(R.string.retry) {
                errorSnackBar?.dismiss()
                errorState.retryCommand.execute()
            }
        }.show()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable("layout_manager_state", gitRepoRecycler.layoutManager?.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        layoutManagerSavedState = savedInstanceState?.getParcelable("layout_manager_state")
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun showRepos(repos: List<GitRepo>) {
            emptyView.gone()
            gitRepoRecycler.visible()
            gitRepositoryRecyclerAdapter.setData(repos)
            gitRepoRecycler.scrollToPosition(0)

            gitRepoRecycler.layoutManager?.onRestoreInstanceState(layoutManagerSavedState)
            layoutManagerSavedState = null
    }

    private fun showEmptyView() {
            emptyView.visible()
            gitRepoRecycler.invisible()
    }
}


