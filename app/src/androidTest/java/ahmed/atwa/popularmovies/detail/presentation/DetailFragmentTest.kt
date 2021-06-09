package ahmed.atwa.popularmovies.detail.presentation

import ahmed.atwa.popularmovies.PopMovApp
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.util.CustomMatcher
import ahmed.atwa.popularmovies.util.CustomMatcher.hasItemAtPosition
import ahmed.atwa.popularmovies.utils.di.DaggerTestAppComponent
import ahmed.atwa.popularmovies.main.presentation.MainActivity
import ahmed.atwa.popularmovies.movies.presentation.MovieAdapter
import android.app.Instrumentation
import android.content.Intent
import android.os.SystemClock
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    private lateinit var expectedIntent: Matcher<Intent>

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
                        "{\"popularity\":2257.847,\"vote_count\":171,\"video\":false,\"poster_path\":\"\\/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg\",\"id\":528085,\"adult\":false,\"backdrop_path\":\"\\/5UkzNSOK561c2QRy2Zr4AkADzLT.jpg\",\"original_language\":\"en\",\"original_title\":\"2067\",\"genre_ids\":[18,878,53],\"title\":\"2067\",\"vote_average\":5.6,\"overview\":\"A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.\",\"release_date\":\"2020-10-01\"}]}"
        ))
        activityTestRule.launchActivity(intent)
        SystemClock.sleep(2000)
        Espresso.onView(withId(R.id.moviesRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(0, ViewActions.click()))
    }


    @Test
    fun initUI() {
        onView(withId(R.id.tv_title)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(CustomMatcher.TextViewValueMatcher("2067")))
    }

    @Test
    fun onSuccess_trailers_fetched() {
        mockServer.enqueue(MockResponse().setResponseCode(200).setBody(
                "{\"id\":528085,\"results\":[" +
                        "{\"id\":\"1\",\"iso_639_1\":\"en\",\"iso_3166_1\":\"US\",\"key\":\"cU5875rHQ8k\",\"name\":\"2067 (2020) Trailer\",\"site\":\"YouTube\",\"size\":1080,\"type\":\"Trailer\"}," +
                        "{\"id\":\"2\",\"iso_639_1\":\"en\",\"iso_3166_1\":\"US\",\"key\":\"cU5875rHQ8k\",\"name\":\"2067 (2020) Trailer2\",\"site\":\"YouTube\",\"size\":1080,\"type\":\"Trailer\"}" +
                        "]}"
        ))
        SystemClock.sleep(2000)
        onView(withId(R.id.recycler_trailer)).check(matches(isDisplayed()))
    }

   /* @Test
    fun onTrailerSelected() {
        Intents.init()
        expectedIntent = IntentMatchers.hasAction(Intent.ACTION_VIEW)
        Intents.intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))

        mockServer.enqueue(MockResponse().setResponseCode(200).setBody(
                "{\"id\":528085,\"results\":[" +
                        "{\"id\":\"5f6ec227ea394900383ed28d\",\"iso_639_1\":\"en\",\"iso_3166_1\":\"US\",\"key\":\"cU5875rHQ8k\",\"name\":\"2067 (2020) Trailer\",\"site\":\"YouTube\",\"size\":1080,\"type\":\"Trailer\"}," +
                        "{\"id\":\"5f6ec227ea394900383ed28d\",\"iso_639_1\":\"en\",\"iso_3166_1\":\"US\",\"key\":\"cU5875rHQ8k\",\"name\":\"2067 (2020) Trailer2\",\"site\":\"YouTube\",\"size\":1080,\"type\":\"Trailer\"}" +
                        "]}"
        ))
        SystemClock.sleep(2000)

        onView(withId(R.id.recycler_trailer))
                .perform(RecyclerViewActions.actionOnItemAtPosition<TrailerAdapter.TrailerViewHolder>(0, CustomMatcher.clickItemWithId(R.id.play_btn)))

        Intents.intended(expectedIntent)
        Intents.release()
    }*/

    @Test
    fun onFailure() {
        mockServer.enqueue(MockResponse().setResponseCode(500))
        SystemClock.sleep(2000)
        onView(withText("No trailers found")).inRoot(RootMatchers.withDecorView(not(Is.`is`(activityTestRule.activity.window.decorView)))).check(matches(ViewMatchers.isDisplayed()))
    }

}