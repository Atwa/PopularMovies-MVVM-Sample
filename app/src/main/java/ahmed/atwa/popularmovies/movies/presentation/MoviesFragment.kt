package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.base.BaseFragment
import ahmed.atwa.popularmovies.main.presentation.MoviesViewModel
import ahmed.atwa.popularmovies.movies.data.Movie
import ahmed.atwa.popularmovies.utils.commons.GridSpacingItemDecoration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesFragment : BaseFragment<MoviesViewModel>(), MovieAdapter.OnItemClick, (CombinedLoadStates) -> Unit {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        mGridLayoutManager.get()?.let {
            it.reverseLayout = false
            it.isItemPrefetchEnabled = false
            moviesRecycler.layoutManager = it
        }
        moviesRecycler.apply {
            setHasFixedSize(true)
            addItemDecoration(mGridSpacingItemDecoration)
            itemAnimator = DefaultItemAnimator()
            mMovieAdapter.setListener(this@MoviesFragment)
            adapter = mMovieAdapter.withLoadStateFooter(
                    footer = MovieStateAdapter { mMovieAdapter.retry() }
            )
        }
        listenForAdapterStates()
    }

    private fun listenForAdapterStates() {
        lifecycleScope.launch {
            getViewModel().movies.collectLatest {
                mMovieAdapter.submitData(it)
            }
        }
        btn_retry.setOnClickListener {
            mMovieAdapter.retry()
        }
        mMovieAdapter.addLoadStateListener(this)
    }

    override fun onMovieClicked(movieEntity: Movie) {
        getViewModel().setSelectedMovie(movieEntity)
        activity?.let { findNavController().navigate(R.id.details, Bundle()) }
    }

    override fun invoke(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading) {
            btn_retry.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
        } else {
            loadingView.visibility = View.GONE
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> {
                    btn_retry.visibility = View.VISIBLE
                    loadState.refresh as LoadState.Error
                }
                else -> null
            }
            errorState?.let {
                Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
            }
        }
    }


}