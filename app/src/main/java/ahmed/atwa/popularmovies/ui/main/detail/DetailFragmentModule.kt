package ahmed.atwa.popularmovies.ui.main.detail

import ahmed.atwa.popularmovies.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

@Module
class DetailFragmentModule {


    @Provides
    internal fun provideDetailFragmentViewModel(appRepository: AppRepository, rxSchedule: RxSchedule): DetailFragmentViewModel {
        return DetailFragmentViewModel(appRepository, rxSchedule)
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: DetailFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.getActivity())
    }

    @Provides
    internal fun provideTrailerAdapter(): TrailerAdapter {
        return TrailerAdapter(ArrayList())
    }

   @Provides
    internal fun detailFragmentViewModelProvider(mainViewModel: DetailFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

}