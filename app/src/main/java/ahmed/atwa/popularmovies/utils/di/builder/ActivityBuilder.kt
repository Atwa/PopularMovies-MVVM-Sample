

package ahmed.atwa.popularmovies.utils.di.builder

import ahmed.atwa.popularmovies.detail.di.DetailFragmentProvider
import ahmed.atwa.popularmovies.main.di.MainActivityModule
import ahmed.atwa.popularmovies.movies.di.MoviesFragmentProvider
import ahmed.atwa.popularmovies.main.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (MoviesFragmentProvider::class), (DetailFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

}