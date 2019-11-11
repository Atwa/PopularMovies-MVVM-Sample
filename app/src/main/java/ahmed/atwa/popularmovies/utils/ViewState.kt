package ahmed.atwa.popularmovies.utils

/**
 * Created by Ahmed Atwa on 11/11/2019.
 */
sealed class UIState {
    object Loading : UIState()
    object HasData : UIState()
    object Error : UIState()
    class MessageText(text: String) : UIState()
    class MessageRes(val resId: Int) : UIState()
}