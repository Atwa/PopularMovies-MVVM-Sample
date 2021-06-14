package ahmed.atwa.popularmovies.movies.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class MovieFilterSource(private val repo: MovieRepo, private val filterText:String) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val movieListResponse = repo.getFilteredPopularMovies(filterText)!!
            LoadResult.Page(
                    data = movieListResponse.results,
                    prevKey = null,
                    nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}