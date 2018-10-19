package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.ui.main.home.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules =[(MainFragmentModule::class)])
    internal abstract fun provideMainFragmentFactory(): MoviesFragment

}