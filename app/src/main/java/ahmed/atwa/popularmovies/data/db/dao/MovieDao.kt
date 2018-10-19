package ahmed.atwa.popularmovies.data.db.dao

import ahmed.atwa.popularmovies.data.api.Movie
import android.arch.persistence.room.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(movie: Movie)

    @Delete
    abstract fun remove(movie: Movie)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    abstract fun loadAllMoviesById(movieId: Int?): List<Movie>

}