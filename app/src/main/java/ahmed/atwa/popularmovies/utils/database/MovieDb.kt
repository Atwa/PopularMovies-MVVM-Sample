package ahmed.atwa.popularmovies.utils.database

import ahmed.atwa.popularmovies.movies.data.MovieLocal
import androidx.room.*
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Singleton
@Database(entities = [(MovieLocal::class)], version = 4, exportSchema = false)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}


