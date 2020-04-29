package com.ljchen17.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.ljchen17.myapplication.fragment.SongListFragment

class SongListAdapter(private var listOfSongs: MutableList<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClickListener: ((song: Song) -> Unit)? = null

    var onSongLongClickListener: ((song: Song) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {

        val itemViewFromALayout = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(itemViewFromALayout)

    }

    override fun getItemCount(): Int {
        return listOfSongs.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        holder.bindView(song)
    }

    fun shuffleSongs(newSong: MutableList<Song>) {
        listOfSongs = newSong
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        listOfSongs.removeAt(position)
        notifyDataSetChanged()
    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val songTitle by lazy {itemView.findViewById<TextView>(R.id.songTitle)}

        private val songArtist by lazy {itemView.findViewById<TextView>(R.id.songArtist)}

        private val songImage by lazy {itemView.findViewById<ImageView>(R.id.ivSongImage)}

        fun bindView (song: Song) {

            songTitle.text = song.title

            songArtist.text = song.artist

            songImage.setImageResource(song.smallImageID)

            itemView.setOnClickListener {
                onSongClickListener?.invoke(song)
            }


        }

    }

    }

