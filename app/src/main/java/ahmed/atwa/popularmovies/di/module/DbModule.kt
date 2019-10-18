/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.source.MovieDao
import ahmed.atwa.popularmovies.data.source.MovieDb
import ahmed.atwa.popularmovies.utils.AppConstants
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 11/8/2018.
 */

@Module
class DbModule{

    @Provides
    @Singleton
    internal fun provideMovieDb(context: Context): MovieDb {
        return Room.databaseBuilder(context, MovieDb::class.java, AppConstants.DB_NAME).fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(context: Context): MovieDao {
        return provideMovieDb(context).movieDao()
    }
}