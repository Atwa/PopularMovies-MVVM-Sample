package ahmed.atwa.popularmovies.domain.useCase

import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import javax.inject.Inject

class GetLikeState @Inject constructor(val repository: MovieRepo)  {

     fun invoke(id: Int): Boolean {
       return repository.getLikeState(id)
    }
}