

package ahmed.atwa.popularmovies.ui.main

import ahmed.atwa.popularmovies.data.AppRepository
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.utils.RxSchedule
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MainViewModel @Inject constructor(appRepository: AppRepository, rxSchedule: RxSchedule) : BaseViewModel(appRepository, rxSchedule) {


}