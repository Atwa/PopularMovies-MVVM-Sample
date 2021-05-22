package ahmed.atwa.popularmovies.movie.presentation


import ahmed.atwa.popularmovies.PopMovApp
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.config.di.DaggerTestAppComponent
import ahmed.atwa.popularmovies.main.presentation.MainActivity
import ahmed.atwa.popularmovies.movies.presentation.MovieAdapter
import android.content.Intent
import android.os.SystemClock
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    private lateinit var mockServer: MockWebServer

    private lateinit var app: PopMovApp

    private lateinit var intent: Intent


    @Before
    fun setup() {

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        app = instrumentation.targetContext.applicationContext as PopMovApp

        val appInjector = DaggerTestAppComponent.builder()
                .application(app)
                .build()
        appInjector.inject(app)
        mockServer = appInjector.getMockWebServer()

        intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        mockServer.enqueue(MockResponse().setResponseCode(200).setBody(
                "{\"page\":1,\"total_results\":10000,\"total_pages\":500,\"results\":[" +
                        "{\"popularity\":2257.847,\"vote_count\":171,\"video\":false,\"poster_path\":\"\\/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg\",\"id\":528085,\"adult\":false,\"backdrop_path\":\"\\/5UkzNSOK561c2QRy2Zr4AkADzLT.jpg\",\"original_language\":\"en\",\"original_title\":\"2067\",\"genre_ids\":[18,878,53],\"title\":\"2067\",\"vote_average\":5.6,\"overview\":\"A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.\",\"release_date\":\"2020-10-01\"}," +
                        "{\"popularity\":2439.455,\"vote_count\":15,\"video\":false,\"poster_path\":\"\\/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg\",\"id\":724989,\"adult\":false,\"backdrop_path\":\"\\/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg\",\"original_language\":\"en\",\"original_title\":\"Hard Kill\",\"genre_ids\":[28,53],\"title\":\"Hard Kill\",\"vote_average\":3.7,\"overview\":\"The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.\",\"release_date\":\"2020-10-23\"}" +
                        "]}"
        ))
        activityTestRule.launchActivity(intent)
        SystemClock.sleep(2000)
    }


    @Test
    fun initUI() {
        onView(withId(R.id.moviesRecycler)).check(matches(isDisplayed()))
    }

    @Test
    fun onSuccess() {
        onView(withId(R.id.pb_loading)).check(doesNotExist());
        onView(withId(R.id.moviesRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(0, click()))
    }

    @Test
    fun onFailure() {
        mockServer.enqueue(MockResponse().setResponseCode(500))
        onView(withId(R.id.pb_loading)).check(doesNotExist())
    }
}