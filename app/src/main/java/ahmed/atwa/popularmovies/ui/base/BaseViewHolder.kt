

package ahmed.atwa.popularmovies.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(position: Int)
}