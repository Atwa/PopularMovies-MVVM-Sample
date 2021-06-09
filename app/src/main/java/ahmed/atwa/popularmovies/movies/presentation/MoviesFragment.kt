package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.base.BaseFragment
import ahmed.atwa.popularmovies.utils.commons.GridSpacingItemDecoration
import ahmed.atwa.popularmovies.main.presentation.MoviesViewModel
import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesFragment : BaseFragment<MoviesViewModel>(), MovieAdapter.OnItemClick {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: Provider<GridLayoutManager>

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MovieAdapter

    override fun getLayoutId(): Int = R.layout.fragment_movies
    override fun getViewModel(): MoviesViewModel = ViewModelProviders.of(requireActivity(), mViewModelFactory).get(MoviesViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override fun initUI() {
        mGridLayoutManager.get()?.let {
            it.reverseLayout = false
            it.isItemPrefetchEnabled = false
            moviesRecycler.layoutManager = it
        }
        moviesRecycler.setHasFixedSize(true)
        moviesRecycler.addItemDecoration(mGridSpacingItemDecoration)
        moviesRecycler.itemAnimator = DefaultItemAnimator()
        moviesRecycler.adapter = mMovieAdapter
        mMovieAdapter.setListener(this)
        showLoading()
        getViewModel().getMovies()
    }

    override fun renderViewState(data: Any) {
        when(data){
            is MoviesViewState.FetchingMoviesSuccess -> mMovieAdapter.addItems(data.movies)
            is MoviesViewState.FetchingMoviesError -> renderFetchingMoviesError()
        }
    }

    private fun renderFetchingMoviesError() {
        showMessage(getString(R.string.fetch_movies_error))
    }

    override fun onMovieClicked(movieEntity: MovieEntity) {
        getViewModel().setSelectedMovie(movieEntity)
        activity?.let { findNavController().navigate(R.id.details, Bundle()) }
    }


}