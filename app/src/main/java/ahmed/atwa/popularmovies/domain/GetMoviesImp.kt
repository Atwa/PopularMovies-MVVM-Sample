package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.data.repo.MovieRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesImp @Inject constructor(val repository: MovieRepo) : GetMovies {

    override  fun invoke() : Flow<List<Movie>?> {
       return repository.getMovies()
    }

}