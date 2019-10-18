

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.repository.MovieRepository
import ahmed.atwa.popularmovies.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(movieRepository: MovieRepository): MainViewModel {
        return MainViewModel(movieRepository)
    }

   /* @Provides
    internal fun mainViewModelProvider(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }*/

}