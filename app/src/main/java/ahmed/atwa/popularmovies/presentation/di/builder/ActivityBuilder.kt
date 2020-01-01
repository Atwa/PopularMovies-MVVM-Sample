

package ahmed.atwa.popularmovies.presentation.di.builder

import ahmed.atwa.popularmovies.presentation.di.module.DetailFragmentProvider
import ahmed.atwa.popularmovies.presentation.di.module.MainActivityModule
import ahmed.atwa.popularmovies.presentation.di.module.MoviesFragmentProvider
import ahmed.atwa.popularmovies.presentation.main.MainActivity
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