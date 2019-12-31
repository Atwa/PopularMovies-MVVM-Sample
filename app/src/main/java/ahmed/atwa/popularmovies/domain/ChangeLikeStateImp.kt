package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.repo.MovieRepo
import javax.inject.Inject

class ChangeLikeStateImp @Inject constructor(val repository: MovieRepo):ChangeLikeState {


    override fun invoke(id: Int, setLiked: Boolean) {
        repository.changeLikeState(id,setLiked)
    }

}