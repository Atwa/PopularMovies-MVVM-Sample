

package ahmed.atwa.popularmovies.movies.di

import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import ahmed.atwa.popularmovies.movies.domain.MovieSourceFactory
import ahmed.atwa.popularmovies.utils.commons.GridSpacingItemDecoration
import ahmed.atwa.popularmovies.movies.presentation.MovieAdapter
import ahmed.atwa.popularmovies.movies.presentation.MoviesFragment
import androidx.recyclerview.widget.GridLayoutManager
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MoviesFragmentModule {

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        return GridLayoutManager(fragment.requireContext(), 2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2, 5, true)
    }

    @Provides
    internal fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter()
    }

}