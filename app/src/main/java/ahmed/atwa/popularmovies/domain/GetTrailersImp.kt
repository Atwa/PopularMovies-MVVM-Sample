package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.remote.model.Trailer
import ahmed.atwa.popularmovies.data.repo.MovieRepo
import ahmed.atwa.popularmovies.data.repo.MovieRepoImp
import javax.inject.Inject

class GetTrailersImp @Inject constructor(val repository: MovieRepo) : GetTrailers  {

    override suspend fun invoke(movieId: Int) : ArrayList<Trailer>? {
        return repository.fetchTrailersApiCall(movieId)
    }
}