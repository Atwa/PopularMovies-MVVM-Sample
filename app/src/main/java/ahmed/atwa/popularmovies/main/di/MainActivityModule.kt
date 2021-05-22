

package ahmed.atwa.popularmovies.main.di

import ahmed.atwa.popularmovies.movies.data.MovieRepoImp
import ahmed.atwa.popularmovies.main.presentation.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(movieRepository: MovieRepoImp): MainViewModel {
        return MainViewModel(movieRepository)
    }

}