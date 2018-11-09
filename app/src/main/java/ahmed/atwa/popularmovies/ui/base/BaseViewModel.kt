package ahmed.atwa.popularmovies.ui.base

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewModel<N>(appRepository: AppRepository, rxSchedule: RxSchedule) : ViewModel() {

    private lateinit var mNavigator: WeakReference<N>
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

    fun getNavigator(): N = mNavigator.get()!!


    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

}