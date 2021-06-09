package ahmed.atwa.popularmovies.main.di

import ahmed.atwa.popularmovies.utils.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.main.presentation.MoviesViewModel
import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MainActivityModule {

    @Provides
    internal fun provideMoviesViewModel(movieRepoImp: MovieRepoImp): MoviesViewModel {
        return MoviesViewModel(movieRepoImp)
    }

    @Provides
    internal fun provideMoviesViewModelFactory(moviesViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesViewModel)
    }

}