package ahmed.atwa.popularmovies.domain

interface ChangeLikeState {

    operator fun invoke(id: Int, setLiked :Boolean)


}