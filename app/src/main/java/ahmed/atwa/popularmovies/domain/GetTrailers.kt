package ahmed.atwa.popularmovies.domain

interface GetTrailers {

    suspend operator fun invoke(movieId: Int)


}