package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.base.BaseFragment
import ahmed.atwa.popularmovies.movies.data.Movie
import ahmed.atwa.popularmovies.utils.commons.GridSpacingItemDecoration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesFragment : BaseFragment<MoviesViewModel>(), MovieAdapter.OnItemClick, (CombinedLoadStates) -> Unit, SearchView.OnQueryTextListener {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: Provider<GridLayoutManager>

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MovieAdapter

    override fun getLayoutId(): Int = R.layout.fragment_movies
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override val viewModel by lazy {
        ViewModelProvider(requireActivity(), mViewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val inflater = requireActivity().menuInflater
        inflater.inflate(R.menu.search_menu, menu);
        val mSearchMenuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = mSearchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = resources.getString(R.string.search_placeholder)
    }

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
        viewModel.movies.observe(viewLifecycleOwner,
                { paging -> lifecycleScope.launch { mMovieAdapter.submitData(paging) } })
        btn_retry.setOnClickListener { mMovieAdapter.retry() }
        mMovieAdapter.addLoadStateListener(this)
    }

    override fun onMovieClicked(movieEntity: Movie) {
        viewModel.setSelectedMovie(movieEntity)
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
            errorState?.error?.localizedMessage?.let { showMessage(it) }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { viewModel.getSearchLiveData().postValue(it) }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
}

