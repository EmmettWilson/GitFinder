package io.github.wilson.emmett.gitfinder

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val gitRepoViewModel: GitRepoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.mainText).text = gitRepoViewModel.getText()

        gitRepoViewModel.getRepositories()
            .subscribe {
            }
    }
}
