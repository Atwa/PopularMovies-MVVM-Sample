package ahmed.atwa.popularmovies.utils.di

import ahmed.atwa.popularmovies.utils.commons.AppConstants
import ahmed.atwa.popularmovies.utils.database.MovieDao
import ahmed.atwa.popularmovies.utils.database.MovieDb
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockDbModule {
        @Provides
        @Singleton
        @Named(AppConstants.DB_NAME_KEY)
        internal fun provideMovieDb(context: Context): MovieDb {
            return Room.databaseBuilder(context, MovieDb::class.java, AppConstants.DB_MOCK_NAME).fallbackToDestructiveMigration()
                    .build()
        }

        @Provides
        @Singleton
        internal fun provideMovieDao(context: Context): MovieDao {
            return provideMovieDb(context).movieDao()
        }
}