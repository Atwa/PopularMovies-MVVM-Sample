package ahmed.atwa.popularmovies.domain.useCase

import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import javax.inject.Inject

class ChangeLikeState @Inject constructor(val repository: MovieRepo) {


     operator fun invoke(id: Int, newLikeState: Boolean) {
        repository.changeLikeState(id,newLikeState)
    }

}