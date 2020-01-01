package ahmed.atwa.popularmovies.presentation.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.remote.Trailer
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

class TrailerAdapter(var mTrailerList: MutableList<Trailer>, val mContext: Context) : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {


    val mLayoutInflater = LayoutInflater.from(mContext);
    lateinit var mListener: TrailerAdapterListener


    override fun getItemCount(): Int {
        return if (mTrailerList.size > 0) {
            mTrailerList.size
        } else {
            0
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_trailer_view, parent, false)
        return TrailerViewHolder(view)
    }

    fun addItems(mList: List<Trailer>) {
        mTrailerList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mTrailerList.clear()
    }


    interface TrailerAdapterListener {

        fun onItemClick(trailer: Trailer)
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
        val trailer = mTrailerList[position]
        holder.tvTrailerName.text = trailer.name
        holder.play_btn.setOnClickListener(View.OnClickListener { mListener.onItemClick(trailer) })
    }


}
