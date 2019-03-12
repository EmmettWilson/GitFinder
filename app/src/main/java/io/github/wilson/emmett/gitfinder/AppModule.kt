package io.github.wilson.emmett.gitfinder

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val MAIN_THREAD_SCHEDULER = "MAIN_THREAD_SCHEDULER"

val appModule = module {

    single<OkHttpClient>{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(get())
            .build()
    }

    single<GithubApi> {
        get<Retrofit>().create<GithubApi>(GithubApi::class.java)
    }

    single {
        GitRepoRepository(get())
    }

    single(MAIN_THREAD_SCHEDULER) {
        AndroidSchedulers.mainThread()
    }

    viewModel { GitRepoViewModel(get(), get(MAIN_THREAD_SCHEDULER)) }
}