package com.main.songfinder.ui.song

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.main.songfinder.R
import com.main.songfinder.SongFinderApplication.Companion.context
import com.main.songfinder.logic.dao.SongResponse
import com.main.songfinder.ui.artist.ArtistActivity

class SongActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[SongViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        val songId = intent.getStringExtra("song_id")

        if (songId != null && viewModel.isSongSaved(songId)) {
            showSongInfo(viewModel.getSavedSong(songId))
            Log.d("SongActivity", "Song loaded by file")
            return
        }

        if (songId != null)
            viewModel.refreshSongId(songId)
        viewModel.songLiveData.observe(this) { result ->
            val song = result.getOrNull()
            if (song != null) {
                viewModel.saveSong(song)
                showSongInfo(song)
            } else {
                Toast.makeText(this, "Failed to get song data", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }

    private fun showSongInfo(song: SongResponse) {
        val songLayout = findViewById<RelativeLayout>(R.id.songLayout)
        val albumImage = findViewById<ImageView>(R.id.albumImage)
        val nameArtist = findViewById<TextView>(R.id.nameArtist)
        val songTitle = findViewById<TextView>(R.id.songTitle)
        val songRelease = findViewById<TextView>(R.id.songRelease)
        val goToLyrics = findViewById<Button>(R.id.goToLyrics)
        val goToAlbum = findViewById<Button>(R.id.goToAlbum)
        val goToArtist = findViewById<Button>(R.id.goToArtist)
        val imageUrl = song.response.song.album.imageUrl
        val albumUrl = song.response.song.album.url
        val songUrl = song.response.song.url
        if (imageUrl.isNotEmpty())
            Glide.with(context).load(imageUrl).into(albumImage)

        nameArtist.text = song.response.song.artits
        songTitle.text = song.response.song.title
        songRelease.text = song.response.song.release
        songLayout.visibility = View.VISIBLE

        goToLyrics.setOnClickListener {
            if (songUrl.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(songUrl))
                startActivity(intent)
            }
        }

        goToAlbum.setOnClickListener {
            if (albumUrl.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(albumUrl))
                startActivity(intent)
            }
        }

        goToArtist.setOnClickListener {
            val intent = Intent(context, ArtistActivity::class.java).apply {
                Log.d("artist_id", song.response.song.artist.id)
                putExtra("artist_id", song.response.song.artist.id)
            }
            startActivity(intent)
        }
    }

}