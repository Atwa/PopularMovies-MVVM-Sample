package ahmed.atwa.popularmovies.presentation.di.module

import ahmed.atwa.popularmovies.data.entity.MovieMapper
import ahmed.atwa.popularmovies.data.local.dataSource.MovieDao
import ahmed.atwa.popularmovies.data.remote.dataSource.MovieApi
import ahmed.atwa.popularmovies.data.remote.dataSource.TrailerApi
import ahmed.atwa.popularmovies.data.repository.MovieRepoImp
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

class MapperModule {

    @Provides
    @Singleton
    internal fun provideMovieDataMapper(): MovieMapper {
        return MovieMapper()
    }

}