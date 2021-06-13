package ahmed.atwa.popularmovies.utils.network

import ahmed.atwa.popularmovies.BuildConfig
import ahmed.atwa.popularmovies.utils.commons.AppConstants.Companion.BASE_URL_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UrlModule {
    @Provides
    @Singleton
    @Named(BASE_URL_KEY)
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}