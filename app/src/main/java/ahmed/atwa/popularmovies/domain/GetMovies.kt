package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.remote.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMovies {

    operator fun invoke(): Flow<List<Movie>?>

}