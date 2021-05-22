

package ahmed.atwa.popularmovies.detail.di

import ahmed.atwa.popularmovies.detail.di.DetailFragmentModule
import ahmed.atwa.popularmovies.detail.presentation.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
abstract class DetailFragmentProvider {

    @ContributesAndroidInjector(modules =[(DetailFragmentModule::class)])
    internal abstract fun provideDetailFragmentFactory(): DetailFragment

}