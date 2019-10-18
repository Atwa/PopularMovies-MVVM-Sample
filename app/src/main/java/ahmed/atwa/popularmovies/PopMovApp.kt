package ahmed.atwa.popularmovies

import ahmed.atwa.popularmovies.di.component.DaggerAppComponent
import android.app.Activity
import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class PopMovApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mCalligraphyConfig: CalligraphyConfig

    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        CalligraphyConfig.initDefault(mCalligraphyConfig)
        setupWorkManagerJob()
    }

    private fun setupWorkManagerJob() {
        val config = Configuration.Builder().setMaxSchedulerLimit(100)
                .setWorkerFactory(workerFactory)
                .build()

        WorkManager.initialize(this, config)
    }

}