/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.remote.MovieApi
import ahmed.atwa.popularmovies.data.local.MovieDao
import ahmed.atwa.popularmovies.data.repo.MovieRepoImp
import ahmed.atwa.popularmovies.data.remote.TrailerApi
import ahmed.atwa.popularmovies.data.repo.MovieRepo
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
    internal fun provideMovieRepository(movieDao: MovieDao, movieApi: MovieApi, trailerApi: TrailerApi): MovieRepo {
        return MovieRepoImp(movieDao, movieApi, trailerApi)
    }

}