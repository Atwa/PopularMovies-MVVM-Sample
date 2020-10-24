

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.local.dataSource.MoviePrefrence
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Ahmed Atwa on 10/19/18.
 */


@Module
class AppModule {


    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    internal fun provideAppPrefrence(mContext: Context): MoviePrefrence {
        return MoviePrefrence(mContext)
    }

}