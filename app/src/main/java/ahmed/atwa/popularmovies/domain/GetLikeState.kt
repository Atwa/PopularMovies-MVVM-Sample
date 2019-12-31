package ahmed.atwa.popularmovies.domain

interface GetLikeState {

    operator fun invoke(id: Int): Boolean
}