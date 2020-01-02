package ahmed.atwa.popularmovies.presentation.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.remote.TrailerRemote
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class TrailerAdapter(var mTrailerRemoteList: MutableList<TrailerRemote>, val mContext: Context) : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {


    val mLayoutInflater = LayoutInflater.from(mContext);
    lateinit var mListener: TrailerAdapterListener


    override fun getItemCount(): Int {
        return if (mTrailerRemoteList.size > 0) {
            mTrailerRemoteList.size
        } else {
            0
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_trailer_view, parent, false)
        return TrailerViewHolder(view)
    }

    fun addItems(mList: List<TrailerRemote>) {
        mTrailerRemoteList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mTrailerRemoteList.clear()
    }


    interface TrailerAdapterListener {

        fun onItemClick(trailerRemote: TrailerRemote)
    }

    inner class TrailerViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTrailerName: TextView
        var play_btn: ImageView

        init {
            tvTrailerName = itemView.findViewById(R.id.trailer_tv) as TextView
            play_btn = itemView.findViewById(R.id.play_btn) as ImageView
        }

    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = mTrailerRemoteList[position]
        holder.tvTrailerName.text = trailer.name
        holder.play_btn.setOnClickListener(View.OnClickListener { mListener.onItemClick(trailer) })
    }


}
