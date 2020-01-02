package ahmed.atwa.popularmovies.presentation.di.module

import ahmed.atwa.popularmovies.presentation.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.repository.MovieRepoImp
import ahmed.atwa.popularmovies.domain.useCase.ChangeLikeState
import ahmed.atwa.popularmovies.domain.useCase.IsMovieLiked
import ahmed.atwa.popularmovies.domain.useCase.GetTrailers
import ahmed.atwa.popularmovies.presentation.detail.DetailFragment
import ahmed.atwa.popularmovies.presentation.detail.DetailFragmentViewModel
import ahmed.atwa.popularmovies.presentation.detail.TrailerAdapter
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
        return GetTrailers(repository)
    }

    @Provides
    internal fun getLikeStateProvider(repository: MovieRepoImp): IsMovieLiked {
        return IsMovieLiked(repository)
    }

    @Provides
    internal fun changeLikeStateProvider(repository: MovieRepoImp): ChangeLikeState {
        return ChangeLikeState(repository)
    }

    @Provides
    internal fun provideDetailFragmentViewModel(getTrailers: GetTrailers, getLikeState: IsMovieLiked, changeLikeState: ChangeLikeState): DetailFragmentViewModel {
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