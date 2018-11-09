/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.api.AppWebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppWebService.BASE_URL)
                .build()
    }


    @Provides
    @Singleton
    internal fun provideAppWebService(retrofit: Retrofit): AppWebService {
        return retrofit.create(AppWebService::class.java)
    }

}