package ahmed.atwa.popularmovies.domain.useCase

import ahmed.atwa.popularmovies.data.remote.TrailerRemote
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import javax.inject.Inject

class GetTrailers @Inject constructor(val repository: MovieRepo)   {

     suspend operator fun invoke(movieId: Int) : ArrayList<TrailerRemote>? {
        return repository.fetchTrailersApiCall(movieId)
    }
}