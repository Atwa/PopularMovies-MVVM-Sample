package ahmed.atwa.popularmovies.di

import ahmed.atwa.popularmovies.ui.main.MainActivity
import ahmed.atwa.popularmovies.ui.main.MainActivityModule
import ahmed.atwa.popularmovies.ui.main.detail.DetailFragmentProvider
import ahmed.atwa.popularmovies.ui.main.home.MainFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (MainFragmentProvider::class), (DetailFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

}