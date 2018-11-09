

package ahmed.atwa.popularmovies.ui.main.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.data.api.Movie
import ahmed.atwa.popularmovies.data.api.Trailer
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.util.Log

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class DetailFragmentViewModel(appRepository: AppRepository, rxSchedule: RxSchedule) : BaseViewModel<DetailsNavigator>(appRepository, rxSchedule) {

    private lateinit var movie: Movie

    var trailerObservableList: ObservableList<Trailer> = ObservableArrayList<Trailer>()

    var trailerListLiveData: MutableLiveData<List<Trailer>> = MutableLiveData()

    var title: ObservableField<String> = ObservableField()

    var overview: ObservableField<String> = ObservableField()

    var releaseDate: ObservableField<String> = ObservableField()

    var imageUrl: ObservableField<String>? = ObservableField()

    var voteAverage: ObservableField<Double> = ObservableField()

    var voteCount: ObservableField<Int> = ObservableField()

    var isLiked: ObservableField<Boolean> = ObservableField()


    fun setMovie(mMovie: Movie) {
        movie = mMovie
        title.set(movie.title)
        overview.set(movie.overview)
        releaseDate.set(movie.release_date)
        voteAverage.set(movie.vote_average)
        voteCount.set(movie.vote_count)
        imageUrl?.set(movie.poster_path)
        isLiked.set(false)
        checkLikeState()
        fetchTrailers()
    }

    private fun checkLikeState() {
        mCompositeDisposable.add(mRepository
                .isMovieLike(movieId = movie.id)
                .subscribeOn(mRxSchedule.io())
                .observeOn(mRxSchedule.ui())
                .subscribe {
                    if (it) isLiked.set(true)
                })
    }

    fun onLikeClick() {
        if (isLiked.get()!!)

            mCompositeDisposable.add(mRepository
                    .removeMovieFromLikes(movie)
                    .subscribeOn(mRxSchedule.io())
                    .observeOn(mRxSchedule.ui())
                    .subscribe {
                        isLiked.set(false)
                        getNavigator().showLikeMessage(R.string.movie_unliked)
                    })
        else

            mCompositeDisposable.add(mRepository
                    .insertMovieToLikes(movie)
                    .subscribeOn(mRxSchedule.io())
                    .observeOn(mRxSchedule.ui())
                    .subscribe {
                        isLiked.set(true)
                        getNavigator().showLikeMessage(R.string.movie_liked)
                    })
    }


    fun fetchTrailers() {
        mIsLoading.set(true)
        mCompositeDisposable.add(mRepository
                .getMovieTrailersApiCall(movieId = movie.id)
                .subscribeOn(mRxSchedule.io())
                .observeOn(mRxSchedule.ui())
                .subscribe({ trailerResponse ->
                    if (trailerResponse?.results != null) {
                        trailerListLiveData.value = trailerResponse.results
                    }
                    mIsLoading.set(false)
                }, { throwable ->
                    Log.v("Atwa", throwable.toString())
                    mIsLoading.set(false)
                }))

    }

    fun addTrailerItemsToList(movies: List<Trailer>) {
        trailerObservableList.clear()
        trailerObservableList.addAll(movies)
    }


}

