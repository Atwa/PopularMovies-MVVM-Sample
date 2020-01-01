package ahmed.atwa.popularmovies.domain.useCase

import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import javax.inject.Inject

class ChangeLikeState @Inject constructor(val repository: MovieRepo) {


     fun invoke(id: Int, setLiked: Boolean) {
        repository.changeLikeState(id,setLiked)
    }

}