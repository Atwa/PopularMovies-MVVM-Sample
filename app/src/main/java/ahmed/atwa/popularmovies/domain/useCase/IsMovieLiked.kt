package ahmed.atwa.popularmovies.domain.useCase

import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import javax.inject.Inject

class IsMovieLiked @Inject constructor(val repository: MovieRepo)  {

     operator fun invoke(id: Int): Boolean {
       return repository.isMovieLiked(id)
    }
}