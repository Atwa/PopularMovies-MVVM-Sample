

package ahmed.atwa.popularmovies.movies.di

import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import ahmed.atwa.popularmovies.config.commons.GridSpacingItemDecoration
import ahmed.atwa.popularmovies.config.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.movies.presentation.MovieAdapter
import ahmed.atwa.popularmovies.movies.presentation.MoviesFragment
import ahmed.atwa.popularmovies.movies.presentation.MoviesViewModel
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MoviesFragmentModule {


    @Provides
    internal fun provideMainFragmentViewModel(movieRepoImp: MovieRepoImp): MoviesViewModel {
        return MoviesViewModel(movieRepoImp)
    }

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        return GridLayoutManager((fragment.activity as Context?)!!, 2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2, 5, true)
    }

    @Provides
    internal fun provideMovieAdapter(context: Context): MovieAdapter {
        return MovieAdapter(ArrayList(), context)
    }


    @Provides
    internal fun mainFragmentViewModelProvider(moviesViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesViewModel)
    }

}