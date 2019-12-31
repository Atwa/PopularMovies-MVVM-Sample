package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.ui.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.repo.MovieRepoImp
import ahmed.atwa.popularmovies.domain.*
import ahmed.atwa.popularmovies.ui.detail.DetailFragment
import ahmed.atwa.popularmovies.ui.detail.DetailFragmentViewModel
import ahmed.atwa.popularmovies.ui.detail.TrailerAdapter
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Module
class DetailFragmentModule {

    @Provides
    internal fun getTrailersProvider(repository: MovieRepoImp): GetTrailers {
        return GetTrailersImp(repository)
    }

    @Provides
    internal fun getLikeStateProvider(repository: MovieRepoImp): GetLikeState {
        return GetLikeStateImp(repository)
    }

    @Provides
    internal fun changeLikeStateProvider(repository: MovieRepoImp): ChangeLikeState {
        return ChangeLikeStateImp(repository)
    }

    @Provides
    internal fun provideDetailFragmentViewModel(getTrailers: GetTrailers, getLikeState: GetLikeState, changeLikeState: ChangeLikeState): DetailFragmentViewModel {
        return DetailFragmentViewModel(getTrailers,getLikeState,changeLikeState)
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: DetailFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.getActivity())
    }

    @Provides
    internal fun provideTrailerAdapter(context: Context): TrailerAdapter {
        return TrailerAdapter(ArrayList(), context)
    }

    @Provides
    internal fun detailFragmentViewModelProvider(mainViewModel: DetailFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

}