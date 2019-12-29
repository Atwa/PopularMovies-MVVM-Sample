package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.source.MovieRepository
import javax.inject.Inject

class GetTrailersImp @Inject constructor(val repository: MovieRepository) : GetTrailers  {

    override fun invoke() {
        repository.
    }
}