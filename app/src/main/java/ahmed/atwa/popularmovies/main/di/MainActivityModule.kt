package ahmed.atwa.popularmovies.main.di

import ahmed.atwa.popularmovies.utils.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.movies.presentation.MoviesViewModel
import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import ahmed.atwa.popularmovies.movies.domain.MovieSourceFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MainActivityModule {

    @Provides
    internal fun provideMoviesViewModel(movieRepoImp: MovieRepoImp,sourceFactory:MovieSourceFactory): MoviesViewModel {
        return MoviesViewModel(movieRepoImp,sourceFactory)
    }

    @Provides
    internal fun provideMoviesViewModelFactory(moviesViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesViewModel)
    }

}