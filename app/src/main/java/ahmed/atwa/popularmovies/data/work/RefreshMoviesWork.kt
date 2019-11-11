package ahmed.atwa.popularmovies.data.work

import ahmed.atwa.popularmovies.data.source.MovieRepository
import ahmed.atwa.popularmovies.utils.AppConstants
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.runBlocking


/**
 * Created by Ahmed Atwa on 10/17/2019.
 */


class RefreshMoviesWork(context: Context, params: WorkerParameters) : Worker(context, params) {

    lateinit var repository: MovieRepository


    override fun doWork(): Result {
        return try {
            runBlocking {
                if (repository.fetchMoviesApiCall() != null) {
                    repository.syncFavWithDb(repository.fetchMoviesApiCall()?.toList()!!)
                    Log.d(AppConstants.DEBUG_TAG, "Every thing is going on so fcking great and the worker is ok")
                    Result.success()
                } else
                    Result.failure()
            }
        } catch (e: Throwable) {
            Result.failure()
        }
    }


}