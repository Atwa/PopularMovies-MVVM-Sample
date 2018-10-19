package ahmed.atwa.popularmovies.ui.main.home

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.data.api.Movie
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import javax.inject.Inject

class MoviesFragmentViewModel @Inject constructor (appRepository: AppRepository, rxSchedule: RxSchedule) : BaseViewModel(appRepository, rxSchedule) {

    var movieObservableList: ObservableList<Movie> = ObservableArrayList<Movie>()

    var movieListLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        fetchMovies()
    }

    fun addMovieItemsToList(movies: List<Movie>) {
        movieObservableList.clear()
        movieObservableList.addAll(movies)
    }

    fun fetchMovies() {
        mIsLoading.set(true)
        mCompositeDisposable.add(mRepository
                .getMoviesApiCall()
                .subscribeOn(mRxSchedule.io())
                .observeOn(mRxSchedule.ui())
                .subscribe({ movieResponse ->
                    if (movieResponse?.results != null) {
                        movieListLiveData.setValue(movieResponse.results)
                    }
                    mIsLoading.set(false)
                }, { throwable ->
                    Log.v("Atwa", throwable.toString())
                    mIsLoading.set(false)
                }))
    }


}