package ahmed.atwa.popularmovies.ui.main.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules =[(MainFragmentModule::class)])
    internal abstract fun provideMainFragmentFactory(): MainFragment

}