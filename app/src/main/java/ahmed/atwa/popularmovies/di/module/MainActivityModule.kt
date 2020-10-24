

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.repository.MovieRepoImp
import ahmed.atwa.popularmovies.presentation.main.MainViewModel
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