package ahmed.atwa.popularmovies.movies.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class MovieFilterSource @Inject constructor(private val repo: MovieRepo) : PagingSource<Int, Movie>() {

    var filterText:String? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return if (filterText == null) LoadResult.Error(Exception(""))
        else try {
            val movieListResponse = repo.getFilteredPopularMovies(filterText!!)!!
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