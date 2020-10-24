package ahmed.atwa.popularmovies.presentation.commons

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestDispatcher : CoroutineDispatcher() {
    override val Main: CoroutineContext = Dispatchers.Unconfined
    override val IO: CoroutineContext = Dispatchers.Unconfined
}