package ahmed.atwa.popularmovies.ui.movies

import ahmed.atwa.popularmovies.BR
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.ui.base.BaseFragment
import ahmed.atwa.popularmovies.ui.base.UIState
import ahmed.atwa.popularmovies.utils.GridSpacingItemDecoration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesFragment : BaseFragment< MoviesFragmentViewModel>(), MovieAdapter.callback {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: GridLayoutManager

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MovieAdapter


    lateinit var mMoviesFragmentViewModel: MoviesFragmentViewModel
    lateinit var mListener: MainFragmentListener


    override fun getLayoutId(): Int = R.layout.fragment_movies
    override fun getViewModel(): MoviesFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MoviesFragmentViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMovieAdapter.setListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        mGridLayoutManager.reverseLayout = false
        mGridLayoutManager.isItemPrefetchEnabled = false
        moviesRecycler.setHasFixedSize(true)
        moviesRecycler.layoutManager = mGridLayoutManager
        moviesRecycler.addItemDecoration(mGridSpacingItemDecoration)
        moviesRecycler.itemAnimator = DefaultItemAnimator()
        moviesRecycler.adapter = mMovieAdapter
    }

    override protected fun observeViewState() {
        loading = loadingView
        super.observeViewState()
        getViewModel().uiState.observe(this, Observer {
            when (it) {
                is UIState.hasData<*> -> {
                    mMovieAdapter.clearItems()
                    mMovieAdapter.addItems(it.data as List<Movie>)
                }
            }
        })
    }

    override fun onItemClick(movie: Movie) {
        mListener.onMovieSelected(movie)
    }

    interface MainFragmentListener {
        fun onMovieSelected(movie: Movie)
    }


}