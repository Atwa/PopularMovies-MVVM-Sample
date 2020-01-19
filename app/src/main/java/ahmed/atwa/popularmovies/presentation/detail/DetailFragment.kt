package ahmed.atwa.popularmovies.presentation.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.domain.mapper.MovieEntity
import ahmed.atwa.popularmovies.data.remote.TrailerRemote
import ahmed.atwa.popularmovies.presentation.base.BaseFragment
import ahmed.atwa.popularmovies.presentation.base.DetailViewState
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Suppress("UNCHECKED_CAST")
@SuppressLint("ValidFragment")
class DetailFragment(val movie: MovieEntity) : BaseFragment<DetailFragmentViewModel>(), TrailerAdapter.TrailerAdapterListener {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager


    @Inject
    lateinit var mTrailerAdapter: TrailerAdapter

    lateinit var mDetailFragmentViewModel: DetailFragmentViewModel
    lateinit var mListener: DetailFragmentListener

    override fun getLayoutId(): Int = R.layout.fragment_detail
    override fun getViewModel(): DetailFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailFragmentViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTrailerAdapter.mListener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }


    private fun setUp() {
        trailersRecycler.setHasFixedSize(true)
        trailersRecycler.layoutManager = mLinearLayoutManager
        trailersRecycler.itemAnimator = DefaultItemAnimator()
        trailersRecycler.adapter = mTrailerAdapter
        initMovieUi(movie)
        getViewModel().fetchMovieDetails(movie)
        observeViewState()
    }


    private fun initMovieUi(movie: MovieEntity) {
        title_tv.text = movie.title
        plot_tv.text = movie.overview
        release_tv.text = "Released in : ${movie.release_date}"
        ratingbar.rating = (movie.vote_average / 2).toFloat()
        count_tv.text = movie.vote_count.toString()
        Glide.with(getBaseActivity())
                .load("http://image.tmdb.org/t/p/w185${movie.poster_path}")
                .into(moviePoster)
        like_img.setOnClickListener{getViewModel().onLikeClick()}
    }


    private fun observeViewState() {
        getViewModel().uiState.observe(this, Observer {
            hideLoading()
            when (it) {
                is DetailViewState.messageRes -> showMessage(getString(it.resId))
                is DetailViewState.errorText -> onError(it.text)
                is DetailViewState.likeState -> renderLikeState(it.isLiked)
                is DetailViewState.trailersFetched<*> -> renderTrailers(it.data as ArrayList<TrailerRemote>)
            }
        })
    }

    private fun renderTrailers(trailers: ArrayList<TrailerRemote>) {
        trailers_loading.visibility = View.GONE
        mTrailerAdapter.addItems(trailers)
    }

    private fun renderLikeState(isLiked: Boolean) {
        like_img.setImageResource(if (isLiked) R.drawable.like else R.drawable.dislike)
    }


    override fun onItemClick(trailerRemote: TrailerRemote) {
        mListener.onTrailerSelected(trailerRemote)
    }

    interface DetailFragmentListener {
        fun onTrailerSelected(trailerRemote: TrailerRemote)
    }


}