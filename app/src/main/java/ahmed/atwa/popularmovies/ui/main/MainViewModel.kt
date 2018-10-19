package ahmed.atwa.popularmovies.ui.main

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.ui.main.detail.DetailFragment
import ahmed.atwa.popularmovies.ui.main.home.MainFragment
import ahmed.atwa.popularmovies.utils.RxSchedule
import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.Fragment
import javax.inject.Inject

class MainViewModel @Inject constructor(appRepository: AppRepository, rxSchedule: RxSchedule) : BaseViewModel(appRepository, rxSchedule) {


}