package ahmed.atwa.popularmovies.ui.detail

import ahmed.atwa.popularmovies.BR
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.data.remote.model.Trailer
import ahmed.atwa.popularmovies.databinding.FragmentDetailBinding
import ahmed.atwa.popularmovies.ui.base.BaseFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@SuppressLint("ValidFragment")
class DetailFragment(val movie: Movie) : BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>(), TrailerAdapter.TrailerAdapterListener {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager


    @Inject
    lateinit var mTrailerAdapter: TrailerAdapter

    lateinit var mDetailFragmentViewModel: DetailFragmentViewModel
    private lateinit var mFragmentDetailBinding: FragmentDetailBinding
    lateinit var mListener: DetailFragmentListener

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.fragment_detail
    override fun getViewModel(): DetailFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailFragmentViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTrailerAdapter.mListener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentDetailBinding = getViewDataBinding()
        setUp()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewModel().uiState.observe(viewLifecycleOwner, Observer {
            if (it is UIState.MessageRes)
                showMessage(getString(it.resId))
        })
        getViewModel().setMovie(movie)
    }


    private fun setUp() {
        mFragmentDetailBinding.trailersRecycler.setHasFixedSize(true)
        mFragmentDetailBinding.trailersRecycler.layoutManager = mLinearLayoutManager
        mFragmentDetailBinding.trailersRecycler.itemAnimator = DefaultItemAnimator()
        mFragmentDetailBinding.trailersRecycler.adapter = mTrailerAdapter

    }


    override fun onItemClick(trailer: Trailer) {
        mListener.onTrailerSelected(trailer)
    }

    interface DetailFragmentListener {
        fun onTrailerSelected(trailer: Trailer)
    }


}