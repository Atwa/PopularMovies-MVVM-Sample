package ahmed.atwa.popularmovies.ui.movies

import ahmed.atwa.popularmovies.BR
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.model.Movie
import ahmed.atwa.popularmovies.databinding.FragmentMoviesBinding
import ahmed.atwa.popularmovies.ui.base.BaseFragment
import ahmed.atwa.popularmovies.utils.GridSpacingItemDecoration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesFragmentViewModel>(), MovieAdapter.MovieAdapterListener {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: GridLayoutManager

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MovieAdapter


    lateinit var mMoviesFragmentViewModel: MoviesFragmentViewModel
    private lateinit var mFragmentMainBinding: FragmentMoviesBinding
    lateinit var mListener: MainFragmentListener


    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.fragment_movies
    override fun getViewModel(): MoviesFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MoviesFragmentViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMovieAdapter.mListener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentMainBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
        mGridLayoutManager.reverseLayout = false
        mGridLayoutManager.isItemPrefetchEnabled = false
        mFragmentMainBinding.moviesRecycler.setHasFixedSize(true)
        mFragmentMainBinding.moviesRecycler.layoutManager = mGridLayoutManager
        mFragmentMainBinding.moviesRecycler.addItemDecoration(mGridSpacingItemDecoration)
        mFragmentMainBinding.moviesRecycler.itemAnimator = DefaultItemAnimator()
        mFragmentMainBinding.moviesRecycler.adapter = mMovieAdapter
    }

    override fun onItemClick(movie: Movie) {
        mListener.onMovieSelected(movie)
    }

    interface MainFragmentListener{
        fun onMovieSelected(movie: Movie)
    }


}