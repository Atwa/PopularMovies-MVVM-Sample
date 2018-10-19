package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.ui.main.home.MoviesFragment
import ahmed.atwa.popularmovies.ui.main.home.MoviesFragmentViewModel
import ahmed.atwa.popularmovies.ui.main.home.MovieAdapter
import ahmed.atwa.popularmovies.ui.main.home.MovieAdapter.MovieAdapterListener
import ahmed.atwa.popularmovies.utils.GridSpacingItemDecoration
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.GridLayoutManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesFragmentModule {

    @Provides
    internal fun provideMainFragmentViewModel(appRepository: AppRepository, rxSchedule: RxSchedule): MoviesFragmentViewModel {
        return MoviesFragmentViewModel(appRepository, rxSchedule)
    }

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
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
    internal fun mainFragmentViewModelProvider(moviesFragmentViewModel: MoviesFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesFragmentViewModel)
    }

}