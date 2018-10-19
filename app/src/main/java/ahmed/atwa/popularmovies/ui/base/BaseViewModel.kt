package ahmed.atwa.popularmovies.ui.base

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(appRepository: AppRepository, rxSchedule: RxSchedule) : ViewModel() {

    val mRepository = appRepository
    val mRxSchedule = rxSchedule
    val mCompositeDisposable = CompositeDisposable()
    var mIsLoading = ObservableBoolean(false)

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

}