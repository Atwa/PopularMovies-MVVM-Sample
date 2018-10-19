package ahmed.atwa.popularmovies.di.builder

import ahmed.atwa.popularmovies.ui.main.MainActivity
import ahmed.atwa.popularmovies.di.module.MainActivityModule
import ahmed.atwa.popularmovies.di.module.DetailFragmentProvider
import ahmed.atwa.popularmovies.di.module.MainFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (MainFragmentProvider::class), (DetailFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

}