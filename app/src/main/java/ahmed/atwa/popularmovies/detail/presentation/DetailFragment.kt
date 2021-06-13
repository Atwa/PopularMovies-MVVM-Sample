package ahmed.atwa.popularmovies.detail.presentation

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.base.BaseFragment
import ahmed.atwa.popularmovies.detail.data.TrailerRemote
import ahmed.atwa.popularmovies.main.presentation.MoviesViewModel
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
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
class DetailFragment : BaseFragment<MoviesViewModel>(), TrailerAdapter.TrailerAdapterListener {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mTrailerAdapter: TrailerAdapter

    override fun getLayoutId(): Int = R.layout.fragment_detail
    override fun getViewModel(): MoviesViewModel = ViewModelProviders.of(requireActivity(), mViewModelFactory).get(MoviesViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTrailerAdapter.setListener(this)
    }

    override fun initUI() {
        recycler_trailer.setHasFixedSize(true)
        recycler_trailer.layoutManager = mLinearLayoutManager
        recycler_trailer.itemAnimator = DefaultItemAnimator()
        recycler_trailer.adapter = mTrailerAdapter
        renderMovieDetails()
    }

    private fun renderMovieDetails() {
        getViewModel().getSelectedMovie()?.apply {
            tv_title.text = title
            tv_plot.text = overview
            tv_rating.text = voteAverage.toString()
            tv_release_date.text = String.format(getString(R.string.released_in), releaseDate)
            tv_votes_count.text = String.format(getString(R.string.votes_count), voteCount.toString())
            rating_bar.rating = (voteAverage / 2).toFloat()
            Glide.with(requireActivity())
                    .load("${getViewModel().posterBaseUrl}${posterPath}")
                    .into(img_poster)
            img_like.setOnClickListener { getViewModel().updateLikeStatus(this) }
            getViewModel().getLikeState(id)
            getViewModel().fetchMovieTrailers(id)
        }
    }

    override fun renderViewState(data: Any) {
        when (data) {
            is DetailViewState.MessageRes -> showMessage(getString(data.resId))
            is DetailViewState.LikeState -> renderLikeState(data.isLiked)
            is DetailViewState.TrailersFetchedSuccess -> renderTrailers(data.trailers)
            is DetailViewState.TrailersFetchedError -> renderFetchingTrailerError()
        }
    }

    private fun renderFetchingTrailerError() {
        trailers_loading.visibility = View.GONE
        showMessage(getString(R.string.fetch_trailers_error))
    }

    private fun renderTrailers(trailers: List<TrailerRemote>) {
        trailers_loading.visibility = View.GONE
        mTrailerAdapter.addItems(trailers)
    }

    private fun renderLikeState(isLiked: Boolean) {
        if (isLiked) R.string.movie_liked else R.string.movie_disliked
        img_like.setImageResource(if (isLiked) R.drawable.like else R.drawable.dislike)
    }


    override fun onTrailerClicked(trailerRemote: TrailerRemote) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${getViewModel().youtubeAppUri}${trailerRemote.key}"))
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${getViewModel().youtubeWebUri}${trailerRemote.key}"))
            startActivity(intent)
        }
    }


}