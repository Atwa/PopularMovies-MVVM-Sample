

package ahmed.atwa.popularmovies.presentation.di.module

import ahmed.atwa.popularmovies.presentation.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.repository.MovieRepoImp
import ahmed.atwa.popularmovies.domain.useCase.GetMovies
import ahmed.atwa.popularmovies.presentation.movies.MovieAdapter
import ahmed.atwa.popularmovies.presentation.movies.MoviesFragment
import ahmed.atwa.popularmovies.presentation.movies.MoviesFragmentViewModel
import ahmed.atwa.popularmovies.presentation.commons.GridSpacingItemDecoration
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
    internal fun getMoviesProvider(repository: MovieRepoImp): GetMovies {
        return GetMovies(repository)
    }


    @Provides
    internal fun provideMainFragmentViewModel(getMovies: GetMovies): MoviesFragmentViewModel {
        return MoviesFragmentViewModel(getMovies)
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
        return MovieAdapter(ArrayList(),context)
    }


    @Provides
    internal fun mainFragmentViewModelProvider(moviesFragmentViewModel: MoviesFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesFragmentViewModel)
    }

}