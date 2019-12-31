package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.remote.model.Trailer

interface GetTrailers {

    suspend operator fun invoke(movieId: Int): ArrayList<Trailer>?


}