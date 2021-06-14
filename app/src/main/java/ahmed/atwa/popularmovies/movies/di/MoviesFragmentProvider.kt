

package ahmed.atwa.popularmovies.movies.di

import ahmed.atwa.popularmovies.movies.di.MoviesFragmentModule
import ahmed.atwa.popularmovies.movies.presentation.MoviesFragment
import ahmed.atwa.popularmovies.utils.network.UrlModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
abstract class MoviesFragmentProvider {

    @ContributesAndroidInjector(modules =[(MoviesFragmentModule::class),(MovieSourceModule::class),])
    internal abstract fun provideMainFragmentFactory(): MoviesFragment

}