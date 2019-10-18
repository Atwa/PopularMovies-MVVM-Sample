package ahmed.atwa.popularmovies.data.work

import ahmed.atwa.popularmovies.data.repository.MovieRepository
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters

/**
 * Created by Ahmed Atwa on 10/17/2019.
 */


class DaggerWorkerFactory(private val movieRepository: MovieRepository) : WorkerFactory() {

    override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {
        val workerKlass = Class.forName(workerClassName).asSubclass(ListenableWorker::class.java)
        val constructor = workerKlass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java)
        val instance = constructor.newInstance(appContext, workerParameters)

        when (instance) {
            is RefreshMoviesWork -> {
                instance.repository = movieRepository
            }
            // optionally, handle other workers
        }

        return instance
    }
}