package ahmed.atwa.popularmovies.detail.presentation

sealed class DetailViewState {
    class TrailersFetched<T : Any>(val data: T) : DetailViewState()
    class LikeState(val isLiked: Boolean) : DetailViewState()
    class MessageRes(val resId: Int) : DetailViewState()
}
