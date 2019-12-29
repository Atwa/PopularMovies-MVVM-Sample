package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.data.repo.MovieRepo
import javax.inject.Inject

class GetMoviesImp @Inject constructor(val repository: MovieRepo) : GetMovies {

    override suspend fun invoke() : ArrayList<Movie> {
       return repository.getMovies()
    }

}