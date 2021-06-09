package ahmed.atwa.popularmovies.detail.presentation

import ahmed.atwa.popularmovies.detail.data.TrailerRemote

sealed class DetailViewState {
    object TrailersFetchedError : DetailViewState()
    class TrailersFetchedSuccess(val trailers:  List<TrailerRemote>) : DetailViewState()
    class LikeState(val isLiked: Boolean) : DetailViewState()
    class MessageRes(val resId: Int) : DetailViewState()
}
