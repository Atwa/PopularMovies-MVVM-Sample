

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.ui.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.repo.MovieRepoImp
import ahmed.atwa.popularmovies.domain.GetMovies
import ahmed.atwa.popularmovies.domain.GetMoviesImp
import ahmed.atwa.popularmovies.ui.movies.MovieAdapter
import ahmed.atwa.popularmovies.ui.movies.MoviesFragment
import ahmed.atwa.popularmovies.ui.movies.MoviesFragmentViewModel
import ahmed.atwa.popularmovies.utils.GridSpacingItemDecoration
import android.content.Context
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
    internal fun getMoviesProvider(repository: MovieRepoImp): GetMovies {
        return GetMoviesImp(repository)
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
        return GridSpacingItemDecoration(2,5,true)
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