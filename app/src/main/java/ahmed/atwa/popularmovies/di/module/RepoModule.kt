/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.data.api.AppWebService
import ahmed.atwa.popularmovies.data.db.AppDatabase
import ahmed.atwa.popularmovies.data.prefrence.AppPrefrence
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 11/8/2018.
 */

@Module
class RepoModule {

    @Provides
    @Singleton
    internal fun provideAppRepository(mContext: Context, appdatabase: AppDatabase, appPrefrence: AppPrefrence, appWebService: AppWebService): AppRepository {
        return AppRepository(mContext, appdatabase, appPrefrence, appWebService)
    }

}