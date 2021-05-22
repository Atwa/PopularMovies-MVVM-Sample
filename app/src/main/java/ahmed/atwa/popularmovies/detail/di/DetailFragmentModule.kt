package ahmed.atwa.popularmovies.detail.di

import ahmed.atwa.popularmovies.config.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import ahmed.atwa.popularmovies.detail.presentation.DetailFragment
import ahmed.atwa.popularmovies.detail.presentation.DetailViewModel
import ahmed.atwa.popularmovies.detail.presentation.TrailerAdapter
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class DetailFragmentModule {

    @Provides
    internal fun provideDetailFragmentViewModel(movieRepoImpl: MovieRepoImp): DetailViewModel {
        return DetailViewModel(movieRepoImpl)
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: DetailFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }

    @Provides
    internal fun provideTrailerAdapter(context: Context): TrailerAdapter {
        return TrailerAdapter(ArrayList(), context)
    }

    @Provides
    internal fun detailFragmentViewModelProvider(mainViewModel: DetailViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

}