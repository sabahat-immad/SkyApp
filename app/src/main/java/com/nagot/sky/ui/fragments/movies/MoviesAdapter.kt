package com.nagot.sky.ui.fragments.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.nagot.sky.R
import com.nagot.sky.models.entity.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(), Filterable {

    var onSearchEmpty: (displayError: Boolean) -> Unit = {}

    private lateinit var mContext: Context

    private lateinit var mOriginalMoviesList: MutableList<Movie>

    private var mMoviesList: MutableList<Movie> = mutableListOf()

    private var exampleFilter: Filter = object : Filter() {

        override fun performFiltering(query: CharSequence?): FilterResults {

            val filteredList: MutableList<Movie> = ArrayList()

            if (query.isNullOrBlank()) {

                filteredList.addAll(mOriginalMoviesList)
            } else {


                filteredList.addAll(

                    mOriginalMoviesList.filter { movie: Movie ->

                        movie.title.contains(query.trim(), true) ||
                                movie.genre.contains(query.trim(), true)
                    }
                )
            }

            return FilterResults().apply {
                values = filteredList
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(query: CharSequence?, filteredResults: FilterResults?) {

            mMoviesList.apply {
                clear()

                filteredResults?.values?.let {
                    addAll(it as MutableList<Movie>)
                }

                onSearchEmpty(isEmpty())
            }

            notifyDataSetChanged()
        }
    }

    fun setMoviesList(moviesList: List<Movie>) {

        mMoviesList = moviesList.toMutableList()

        mOriginalMoviesList = ArrayList(mMoviesList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        mContext = parent.context

        return MoviesViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {

        return mMoviesList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        holder.onBind(mMoviesList[position])
    }

    override fun getFilter(): Filter {

        return exampleFilter
    }

    inner class MoviesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var movie: Movie

        fun onBind(movie: Movie) {

            this.movie = movie

            itemView.run {

                genre_tv.text = movie.genre

                movie.poster.let {

                    Glide.with(mContext)
                        .load(it)
                        .listener(
                            GlidePalette.with(it)
                                .use(BitmapPalette.Profile.VIBRANT_LIGHT)
                                .intoBackground(genre_tv)
                                .crossfade(true)
                        )
                        .error(
                            Glide.with(movie_poster).load(R.drawable.image_not_found)
                        )
                        .into(movie_poster)
                }
            }
        }
    }
}