package ahmed.atwa.popularmovies.utils.database

import ahmed.atwa.popularmovies.movies.data.MovieLocal
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieLocal)

    @Query("SELECT id FROM movies")
    fun fetchFavouriteMovies(): List<Int?>

    @Delete()
    fun removeMovie(movie: MovieLocal)

}