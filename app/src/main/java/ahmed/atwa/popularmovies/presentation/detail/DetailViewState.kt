package ahmed.atwa.popularmovies.presentation.detail

sealed class DetailViewState {
    class TrailersFetched<T : Any>(val data: T) : DetailViewState()
    class LikeState(val isLiked: Boolean) : DetailViewState()
    class MessageRes(val resId: Int) : DetailViewState()
}
