package ahmed.atwa.popularmovies.ui.main.home

import ahmed.atwa.popularmovies.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.ui.main.home.MovieAdapter.MovieAdapterListener
import ahmed.atwa.popularmovies.utils.GridSpacingItemDecoration
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.GridLayoutManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainFragmentModule {

    @Provides
    internal fun provideMainFragmentViewModel(appRepository: AppRepository, rxSchedule: RxSchedule): MainFragmentViewModel{
        return MainFragmentViewModel(appRepository, rxSchedule)
    }

    @Provides
    internal fun provideGridLayoutManager(fragment: MainFragment): GridLayoutManager {
        return GridLayoutManager(fragment.getActivity(),2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2,5,true)
    }

    @Provides
    internal fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter(ArrayList())
    }

    @Provides
    @Singleton
    internal fun provideMovieAdapterListener(listener: MovieAdapterListener): MovieAdapterListener {
        return listener
    }


    @Provides
    internal fun mainFragmentViewModelProvider(mainFragmentViewModel: MainFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainFragmentViewModel)
    }

}