package ahmed.atwa.popularmovies.utils.database

import ahmed.atwa.popularmovies.movies.data.Movie
import androidx.room.*
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Singleton
@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}


