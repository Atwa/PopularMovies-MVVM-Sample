package ahmed.atwa.popularmovies.movies.domain

data class MovieEntity(
        var id: Int,
        var posterPath: String?,
        var popularity: Double,
        var voteCount: Int,
        var video: Boolean,
        var voteAverage: Double,
        var title: String?,
        var backdropPath: String? = "null",
        var overview: String?,
        var releaseDate: String?
)