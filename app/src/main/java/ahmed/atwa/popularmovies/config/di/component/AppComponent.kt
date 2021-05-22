package ahmed.atwa.popularmovies.config.di.component

import ahmed.atwa.popularmovies.PopMovApp
import ahmed.atwa.popularmovies.config.database.DbModule
import ahmed.atwa.popularmovies.config.di.module.AppModule
import ahmed.atwa.popularmovies.config.di.module.RepoModule
import ahmed.atwa.popularmovies.config.di.module.UrlModule
import ahmed.atwa.popularmovies.config.network.NetworkModule
import ahmed.atwa.popularmovies.config.di.builder.ActivityBuilder
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (DbModule::class),
    (NetworkModule::class), (UrlModule::class),(RepoModule::class), (ActivityBuilder::class)])

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: PopMovApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}