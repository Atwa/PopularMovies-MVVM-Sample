

package ahmed.atwa.popularmovies.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    abstract fun onBind(position: Int)
}