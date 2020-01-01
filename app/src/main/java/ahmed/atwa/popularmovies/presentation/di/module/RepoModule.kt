/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.presentation.di.module

import ahmed.atwa.popularmovies.data.entity.MovieMapper
import ahmed.atwa.popularmovies.data.remote.dataSource.MovieApi
import ahmed.atwa.popularmovies.data.local.dataSource.MovieDao
import ahmed.atwa.popularmovies.data.repository.MovieRepoImp
import ahmed.atwa.popularmovies.data.remote.dataSource.TrailerApi
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
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
                                        trailerApi: TrailerApi,
                                        movieMapper: MovieMapper): MovieRepo {
        return MovieRepoImp(movieDao, movieApi, trailerApi,movieMapper)
    }

}