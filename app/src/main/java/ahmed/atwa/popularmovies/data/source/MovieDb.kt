package ahmed.atwa.popularmovies.data.source

import ahmed.atwa.popularmovies.data.model.Movie
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

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movies order by popularity DESC")
    fun fetchAllMovies(): List<Movie>

    @Query("SELECT is_favourite FROM movies WHERE id = :movieId ")
    fun isMovieLiked(movieId: Int): List<Int?>

    @Query("update movies set is_favourite = 1 where id = :movieId")
    fun setMovieLiked(movieId: Int): Int

    @Query("update movies set is_favourite = 0  where id = :movieId ")
    fun setMovieUnLiked(movieId: Int): Int

}
