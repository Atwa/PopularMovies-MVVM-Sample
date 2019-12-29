

package ahmed.atwa.popularmovies.di.module

import ahmed.atwa.popularmovies.ui.commons.ViewModelProviderFactory
import ahmed.atwa.popularmovies.data.repo.MovieRepoImp
import ahmed.atwa.popularmovies.domain.GetTrailers
import ahmed.atwa.popularmovies.domain.GetTrailersImp
import ahmed.atwa.popularmovies.ui.detail.DetailFragment
import ahmed.atwa.popularmovies.ui.detail.DetailFragmentViewModel
import ahmed.atwa.popularmovies.ui.detail.TrailerAdapter
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
    internal fun getMoviesProvider(repository: MovieRepoImp): GetTrailers {
        return GetTrailersImp(repository)
    }

    @Provides
    internal fun provideDetailFragmentViewModel(getTrailers: GetTrailers): DetailFragmentViewModel {
        return DetailFragmentViewModel(getTrailers)
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