

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.ui.main.MainViewModel
import ahmed.atwa.popularmovies.utils.RxSchedule
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(appRepository: AppRepository, rxSchedule: RxSchedule): MainViewModel {
        return MainViewModel(appRepository, rxSchedule)
    }

   /* @Provides
    internal fun mainViewModelProvider(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }*/

}