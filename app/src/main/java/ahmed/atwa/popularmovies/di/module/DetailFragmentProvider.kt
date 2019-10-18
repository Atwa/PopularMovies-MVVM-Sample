

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.ui.detail.DetailFragment
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