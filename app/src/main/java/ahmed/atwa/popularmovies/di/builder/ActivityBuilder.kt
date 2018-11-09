

package ahmed.atwa.popularmovies.di.builder

import ahmed.atwa.popularmovies.ui.MainActivity
import ahmed.atwa.popularmovies.di.module.MainActivityModule
import ahmed.atwa.popularmovies.di.module.DetailFragmentProvider
import ahmed.atwa.popularmovies.di.module.MoviesFragmentProvider
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