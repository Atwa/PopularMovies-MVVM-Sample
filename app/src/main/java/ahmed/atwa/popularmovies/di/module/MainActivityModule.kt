package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.ui.main.MainViewModel
import ahmed.atwa.popularmovies.utils.RxSchedule
import dagger.Module
import dagger.Provides

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