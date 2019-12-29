package ahmed.atwa.popularmovies.ui.base

sealed class UIState {
    object loading : UIState()
    class hasData<T : Any>(val data: T) : UIState()
    class errorText(val text: String) : UIState()
    class errorRes(val resId: Int) : UIState()
    class messageText(val text: String) : UIState()
    class messageRes(val resId: Int) : UIState()
}