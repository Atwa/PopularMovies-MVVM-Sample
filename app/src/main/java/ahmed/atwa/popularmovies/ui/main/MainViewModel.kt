package ahmed.atwa.popularmovies.ui.main

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.utils.RxSchedule
import javax.inject.Inject

class MainViewModel @Inject constructor(appRepository: AppRepository, rxSchedule: RxSchedule) : BaseViewModel(appRepository, rxSchedule) {


}