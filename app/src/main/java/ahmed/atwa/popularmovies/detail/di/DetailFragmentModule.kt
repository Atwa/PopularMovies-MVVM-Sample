package ahmed.atwa.popularmovies.detail.di

import ahmed.atwa.popularmovies.detail.presentation.DetailFragment
import ahmed.atwa.popularmovies.detail.presentation.TrailerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class DetailFragmentModule {

    @Provides
    internal fun provideLinearLayoutManager(fragment: DetailFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }

    @Provides
    internal fun provideTrailerAdapter(): TrailerAdapter {
        return TrailerAdapter(ArrayList())
    }

}