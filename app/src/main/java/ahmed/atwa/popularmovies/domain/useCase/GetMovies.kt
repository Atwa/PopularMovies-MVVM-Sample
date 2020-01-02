package ahmed.atwa.popularmovies.domain.useCase

import ahmed.atwa.popularmovies.data.entity.MovieEntity
import ahmed.atwa.popularmovies.data.remote.MovieRemote
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovies @Inject constructor(val repository: MovieRepo)  {

      fun invoke() : Flow<List<MovieEntity>?> {
       return repository.getMovies()
    }

}