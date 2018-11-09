

package ahmed.atwa.popularmovies.ui.main.detail

import ahmed.atwa.popularmovies.BR
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.api.Movie
import ahmed.atwa.popularmovies.data.api.Trailer
import ahmed.atwa.popularmovies.databinding.FragmentDetailBinding
import ahmed.atwa.popularmovies.ui.base.BaseFragment
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@SuppressLint("ValidFragment")
class DetailFragment(val movie: Movie) : BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>() ,TrailerAdapter.TrailerAdapterListener ,DetailsNavigator{



    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager


    @Inject
    lateinit var mTrailerAdapter: TrailerAdapter

    lateinit var mDetailFragmentViewModel: DetailFragmentViewModel
    private lateinit var mFragmentDetailBinding: FragmentDetailBinding
    lateinit var mListener: DetailFragment.DetailFragmentListener

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun getViewModel(): DetailFragmentViewModel {
        mDetailFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailFragmentViewModel::class.java)
        return mDetailFragmentViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTrailerAdapter.mListener = this
        mDetailFragmentViewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentDetailBinding = getViewDataBinding()
        setUp()
        subscribeToLiveData()
        mDetailFragmentViewModel.setMovie(movie)
    }


    private fun setUp() {
        mFragmentDetailBinding.trailersRecycler.setHasFixedSize(true)
        mFragmentDetailBinding.trailersRecycler.layoutManager = mLinearLayoutManager
        mFragmentDetailBinding.trailersRecycler.itemAnimator = DefaultItemAnimator()
        mFragmentDetailBinding.trailersRecycler.adapter = mTrailerAdapter

    }

    private fun subscribeToLiveData() {
        mDetailFragmentViewModel.trailerListLiveData.observe(this, Observer {mDetailFragmentViewModel.addTrailerItemsToList(it!!)} )
    }


    override fun onItemClick(trailer: Trailer) {
        mListener.onTrailerSelected(trailer)
    }

    interface DetailFragmentListener{
        fun onTrailerSelected(trailer: Trailer)
    }

    override fun showLikeMessage(message: Int) {
        showMessage(getString(message))
    }

}