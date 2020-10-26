package ahmed.atwa.popularmovies.di

import ahmed.atwa.popularmovies.PopMovApp
import ahmed.atwa.popularmovies.di.builder.ActivityBuilder
import ahmed.atwa.popularmovies.di.component.AppComponent
import ahmed.atwa.popularmovies.di.module.AppModule
import ahmed.atwa.popularmovies.di.module.DbModule
import ahmed.atwa.popularmovies.di.module.NetworkModule
import ahmed.atwa.popularmovies.di.module.RepoModule
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DaggerApplication
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    DbModule::class,
    NetworkModule::class,
    RepoModule::class,
    ActivityBuilder::class,
    MockUrlModule::class
]
)
interface TestAppComponent : AppComponent {

    override fun inject(app: PopMovApp)

    override fun inject(instance: DaggerApplication)

    fun getMockWebServer(): MockWebServer

    @Component.Builder
    interface Builder {

        /**
         * [BindsInstance] annotation is used for, if you want to bind particular object or instance
         * of an object through the time of component construction
         */
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

}