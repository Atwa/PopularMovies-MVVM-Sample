/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.db.AppDatabase
import ahmed.atwa.popularmovies.utils.AppConstants
import android.arch.persistence.room.Room
import android.content.Context
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
    internal fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DB_NAME).fallbackToDestructiveMigration()
                .build()
    }
}