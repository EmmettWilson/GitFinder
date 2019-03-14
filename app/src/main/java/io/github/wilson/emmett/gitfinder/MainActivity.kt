package io.github.wilson.emmett.gitfinder

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import io.github.wilson.emmett.gitfinder.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val gitRepoViewModel: GitRepoViewModel by viewModel()
    private val gitRepositoryRecyclerAdapter = GitRepositoryRecyclerAdapter()
    private val errorSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gitRepoRecycler.adapter = gitRepositoryRecyclerAdapter
        gitRepoRecycler.layoutManager = LinearLayoutManager(this)

        gitRepoViewModel.gitRepositories.observe(this, Observer {
            if (it.isEmpty()) {
                showEmptyView()
            } else {
                showRepos(it)
            }
        })

        gitRepoViewModel.getErrorState().observe(this, Observer {
            showError(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val white = ContextCompat.getColor(this, R.color.white)
        menu.findItem(R.id.action_search)?.icon?.setTint(white)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search) {
            errorSnackBar?.dismiss()
            SearchActivity.launch(this)
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun showError(errorState: ErrorState) {
        Snackbar.make(activity_main_constraint, errorState.message, Snackbar.LENGTH_INDEFINITE).apply {
            setActionTextColor(ContextCompat.getColor(context, R.color.roseGold))
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.onyx))
            setAction(R.string.retry) {
                errorSnackBar?.dismiss()
                errorState.retryCommand.execute()
            }
        }.show()
    }

    private fun showRepos(repos: List<GitRepo>) {
        emptyView.gone()
        gitRepoRecycler.visible()
        gitRepositoryRecyclerAdapter.setData(repos)
    }

    private fun showEmptyView() {
        emptyView.visible()
        gitRepoRecycler.invisible()
    }
}


