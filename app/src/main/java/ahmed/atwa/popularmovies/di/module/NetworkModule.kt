/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.source.MovieApi
import ahmed.atwa.popularmovies.data.source.TrailerApi
import ahmed.atwa.popularmovies.utils.AppConstants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 11/8/2018.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }


    @Provides
    @Singleton
    internal fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideTrailerApi(retrofit: Retrofit): TrailerApi {
        return retrofit.create(TrailerApi::class.java)
    }

}