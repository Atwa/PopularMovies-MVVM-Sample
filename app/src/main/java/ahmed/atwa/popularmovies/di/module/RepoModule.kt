/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.source.MovieApi
import ahmed.atwa.popularmovies.data.source.MovieDao
import ahmed.atwa.popularmovies.data.source.MovieRepository
import ahmed.atwa.popularmovies.data.source.TrailerApi
import ahmed.atwa.popularmovies.data.work.DaggerWorkerFactory
import androidx.work.WorkerFactory
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
    internal fun provideMovieRepository(movieDao: MovieDao, movieApi: MovieApi, trailerApi: TrailerApi): MovieRepository {
        return MovieRepository(movieDao, movieApi, trailerApi)
    }

    @Provides
    @Singleton
    fun workerFactory(movieRepository: MovieRepository): WorkerFactory {
        return DaggerWorkerFactory(movieRepository)
    }

}