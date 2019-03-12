package io.github.wilson.emmett.gitfinder

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.wilson.emmett.gitfinder.domain.GitRepo
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), Observer<List<GitRepo>> {
    private val gitRepoViewModel: GitRepoViewModel by viewModel()

    private val gitRepositoryRecyclerAdapter = GitRepositoryRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            gitRepoViewModel.searchRepositories(searchText.text.toString())
        }
        gitRepoRecycler.adapter = gitRepositoryRecyclerAdapter
        gitRepoRecycler.layoutManager = LinearLayoutManager(this)
        gitRepoViewModel.getRepositories().observe(this, this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable("layout_manager_state", gitRepoRecycler.layoutManager?.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }

    private var layoutManagerSavedState: Parcelable? = null


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        layoutManagerSavedState = savedInstanceState?.getParcelable("layout_manager_state")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onChanged(repos: List<GitRepo>?) {
        if (repos == null || repos.isEmpty()) {

        } else {
            gitRepositoryRecyclerAdapter.setData(repos)
            gitRepoRecycler.scrollToPosition(0)
            gitRepoRecycler.layoutManager?.onRestoreInstanceState(layoutManagerSavedState)
            layoutManagerSavedState = null
        }
    }

}

