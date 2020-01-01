

package ahmed.atwa.popularmovies.presentation.di.module

import ahmed.atwa.popularmovies.presentation.movies.MoviesFragment
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