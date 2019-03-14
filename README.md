# GitFinder
## An example app for searching github content that acted as a testbed for trying out new technologies and architectures.

# Demo Video
<img src="https://github.com/emmettwilson/gitfinder/raw/master/demoVideo.gif" width="192">

## Dependency Injection using [Koin](https://github.com/InsertKoinIO/koin)
This is a nice alternative to Dagger2 for applications using kotlin. The biggest benefit I found here was how easy it was to replace implementations of injected fields (like for Android `Activities`, `Services`, `etc`) in unit tests. 
```kotlin
private val gitRepoViewModel = mock<GitRepoViewModel>()

@Before
fun setUp() {
 loadKoinModules(listOf(module(override = true) {
    viewModel { gitRepoViewModel }
  }))
 }
```

The set up of the modules was really straight forward. I feel this will have a much quicker learning curve than Dagger2.

## Using Architecture Components to drive a Unidirectional Data Flow
1. The goal of the UI is to read only observe current application state from disk
  * `Room` database populates `LiveData` via a `ViewModel`
  * Some memory state such as current error can be emitted using RxJava2
2. UI Interactions are decoupled from presentation and use the `Command` pattern to execute user intent. This should allow easy swaps of `IntentServices`, `WorkManager`, `SyncAdapter` etc to support executing tasks in many different situations such as offline. The commands also allow robust retry which is demonstrated here.
3. Commands have the responsibility performing background operations, and updating the application state.

## The "Test Anywhere" philosophy of `androidx.test`
I enjoyed returning to Robolectric style testing. It was obvious early on that you cannot simply move tests seemlessly between `androidTest` and `test` source sets. I quickly shied away from continuing with espresso assertions as they did not allow `isDisplayed()` among other assertions which are necessary for things like configuration change and maintaining scroll state in a `RecyclerView`. I think the espresso assertions are very promising though as it will allow us to start testing with Robolectric, but easily migrate to Instrumented Tests when memory consumption becomes a problem as our tests suite grows or breaking changes are introduced to the framework.

