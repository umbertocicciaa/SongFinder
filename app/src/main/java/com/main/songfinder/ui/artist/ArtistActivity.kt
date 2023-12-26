package com.main.songfinder.ui.artist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.main.songfinder.R
import com.main.songfinder.SongFinderApplication
import com.main.songfinder.logic.dao.artist.ArtistResponse

/**
 *  Questi classe si occupa dell'activity relativa alla visualizzazione degli artisti
 *  @author umbertodomenicociccia
 * */
class ArtistActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[ArtistViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        val artistId = intent.getStringExtra("artist_id")

        if (artistId != null && viewModel.isArtistSaved(artistId)) {
            showArtistInfo(viewModel.getSavedArtist(artistId))
            Log.d("ArtistActivity", "Artist loaded by file")
            return
        }

        if (artistId != null)
            viewModel.refreshArtist(artistId)
        viewModel.artistLiveData.observe(this) { result ->
            val artist = result.getOrNull()
            if (artist != null) {
                viewModel.saveArtist(artist)
                showArtistInfo(artist)
            } else {
                Toast.makeText(this, "Failed to get artist data", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }

    }

    private fun showArtistInfo(artist: ArtistResponse) {
        val artistImage: ImageView = findViewById(R.id.artistImage)
        val nameArtist: TextView = findViewById(R.id.nameArtist)
        val goToArtist: Button = findViewById(R.id.goToArtist)
        val imageUrl = artist.response.artist.urlImage
        val artistUrl = artist.response.artist.url
        val layout: RelativeLayout = findViewById(R.id.artistLayout)
        if (imageUrl.isNotEmpty())
            Glide.with(SongFinderApplication.context).load(imageUrl).into(artistImage)
        nameArtist.text = artist.response.artist.name
        goToArtist.setOnClickListener {
            if (artistUrl.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(artistUrl))
                startActivity(intent)
            }
        }
        layout.visibility = View.VISIBLE
    }

}