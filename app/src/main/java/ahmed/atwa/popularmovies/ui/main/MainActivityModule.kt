package ahmed.atwa.popularmovies.ui.main

import ahmed.atwa.popularmovies.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named

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