package ahmed.atwa.popularmovies.movies.di

import ahmed.atwa.popularmovies.movies.data.MovieFilterSource
import ahmed.atwa.popularmovies.movies.data.MoviePagingSource
import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import ahmed.atwa.popularmovies.movies.domain.MovieSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieSourceModule {
    @Provides
    internal fun provideMoviesSource(): MovieSourceFactory {
        return MovieSourceFactory()
    }

    @Provides
    @Singleton
    internal fun provideMoviesPagingSource(movieRepoImp: MovieRepoImp): MoviePagingSource {
        return MoviePagingSource(movieRepoImp)
    }

    @Provides
    @Singleton
    internal fun provideMoviesFilterSource(movieRepoImp: MovieRepoImp): MovieFilterSource {
        return MovieFilterSource(movieRepoImp)
    }
}