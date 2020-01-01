package ahmed.atwa.popularmovies.domain.useCase

import ahmed.atwa.popularmovies.data.remote.Trailer
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import javax.inject.Inject

class GetTrailers @Inject constructor(val repository: MovieRepo)   {

     suspend fun invoke(movieId: Int) : ArrayList<Trailer>? {
        return repository.fetchTrailersApiCall(movieId)
    }
}