

package ahmed.atwa.popularmovies.movies.di

import ahmed.atwa.popularmovies.movies.di.MoviesFragmentModule
import ahmed.atwa.popularmovies.movies.presentation.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
abstract class MoviesFragmentProvider {

    @ContributesAndroidInjector(modules =[(MoviesFragmentModule::class)])
    internal abstract fun provideMainFragmentFactory(): MoviesFragment

}