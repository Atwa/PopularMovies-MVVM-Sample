/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.utils.di.module

import ahmed.atwa.popularmovies.detail.data.TrailerApi
import ahmed.atwa.popularmovies.movies.data.MovieApi
import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import ahmed.atwa.popularmovies.utils.database.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/17/2019.
 */

@Module
class RepoModule {

    @Provides
    @Singleton
    internal fun provideMovieRepository(movieDao: MovieDao,
                                        movieApi: MovieApi,
                                        trailerApi: TrailerApi): MovieRepo {
        return MovieRepoImp(movieDao, movieApi, trailerApi)
    }

}