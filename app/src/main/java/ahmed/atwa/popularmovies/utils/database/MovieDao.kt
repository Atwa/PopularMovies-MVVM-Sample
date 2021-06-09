package ahmed.atwa.popularmovies.utils.database

import ahmed.atwa.popularmovies.movies.data.MovieLocal
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieLocal>)

    @Query("SELECT * FROM movies order by popularity DESC")
    fun fetchAllMovies(): List<MovieLocal>

    @Query("SELECT is_favourite FROM movies WHERE id = :movieId ")
    fun isMovieLiked(movieId: Int): List<Int?>

    @Query("update movies set is_favourite = 1 where id = :movieId")
    fun setMovieLiked(movieId: Int): Int

    @Query("update movies set is_favourite = 0  where id = :movieId ")
    fun setMovieUnLiked(movieId: Int): Int

}