package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.repo.MovieRepo
import javax.inject.Inject

class GetLikeStateImp @Inject constructor(val repository: MovieRepo) : GetLikeState {

    override fun invoke(id: Int): Boolean {
       return repository.getLikeState(id)
    }
}