

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.source.MovieRepository
import ahmed.atwa.popularmovies.ui.movies.MovieAdapter
import ahmed.atwa.popularmovies.ui.movies.MovieAdapter.MovieAdapterListener
import ahmed.atwa.popularmovies.ui.movies.MoviesFragment
import ahmed.atwa.popularmovies.ui.movies.MoviesFragmentViewModel
import ahmed.atwa.popularmovies.utils.GridSpacingItemDecoration
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MoviesFragmentModule {

    @Provides
    internal fun provideMainFragmentViewModel(movieRepository: MovieRepository): MoviesFragmentViewModel {
        return MoviesFragmentViewModel(movieRepository)
    }

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        return GridLayoutManager(fragment.activity!!, 2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2,5,true)
    }

    @Provides
    internal fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter(ArrayList())
    }

    @Provides
    @Singleton
    internal fun provideMovieAdapterListener(listener: MovieAdapterListener): MovieAdapterListener {
        return listener
    }


    @Provides
    internal fun mainFragmentViewModelProvider(moviesFragmentViewModel: MoviesFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesFragmentViewModel)
    }

}