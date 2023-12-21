package com.main.songfinder.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.main.songfinder.R
import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.Hits

class SearchAdapter(private val fragment: Fragment, private val searchList: List<Hits>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameArtist: TextView = view.findViewById(R.id.nameArtist)
        val songTitle: TextView = view.findViewById(R.id.songTitle)
        val songImage: ImageView = view.findViewById(R.id.songImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val searchHits = searchList[position]
        holder.nameArtist.text = searchHits.result.nameArtist
        holder.songTitle.text = searchHits.result.title
        val imageUrl = searchHits.result.imageUrl
        Glide.with(SongFinderApplication.context).load(imageUrl).into(holder.songImage)
    }

    override fun getItemCount() = searchList.size


}