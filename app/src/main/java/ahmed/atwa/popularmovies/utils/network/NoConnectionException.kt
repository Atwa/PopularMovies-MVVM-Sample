package ahmed.atwa.popularmovies.utils.network

import java.io.IOException

/**
 * Created by Ahmed Atwa on 11/7/2019.
 */
class NoConnectionException : IOException() {

    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"
}