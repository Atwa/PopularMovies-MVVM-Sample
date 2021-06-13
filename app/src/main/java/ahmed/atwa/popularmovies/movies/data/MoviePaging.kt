package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.utils.network.ResultType
import androidx.paging.PagingSource
import androidx.paging.PagingState

class MoviePaging(private val repo: MovieRepo) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = (repo.getPopularMovies(nextPage) as ResultType.Success).data

            LoadResult.Page(
                    data = movieListResponse.results,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (nextPage < movieListResponse.total_pages)
                        movieListResponse.page?.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}