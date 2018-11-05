package com.example.kotlin.myapplication.model.repository.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.example.kotlin.myapplication.model.data.Rating

@Entity
class MovieEntity(
    title: String,
    year: String,
    rated: String,
    released: String,
    runtime: String,
    genre: String,
    director: String,
    writer: String,
    actors: String,
    plot: String,
    language: String,
    country: String,
    awards: String,
    poster: String,
    ratings: List<Rating>,
    metascore: String,
    imdbRating: String,
    imdbVotes: String,
    imdbID: String,
    type: String,
    dvd: String,
    boxOffice: String,
    production: String,
    website: String,
    response: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "Title")
    var title: String? = title

    @ColumnInfo(name = "Year")
    var year: String? = year

    @ColumnInfo(name = "Rated")
    var rated: String? = rated

    @ColumnInfo(name = "Released")
    var released: String? = released

    @ColumnInfo(name = "Runtime")
    var runtime: String? = runtime

    @ColumnInfo(name = "Genre")
    var genre: String? = genre

    @ColumnInfo(name = "Director")
    var director: String? = director

    @ColumnInfo(name = "Writer")
    var writer: String? = writer

    @ColumnInfo(name = "Actors")
    var actors: String? = actors

    @ColumnInfo(name = "Plot")
    var plot: String? = plot

    @ColumnInfo(name = "Language")
    var language: String? = language

    @ColumnInfo(name = "Country")
    var country: String? = country

    @ColumnInfo(name = "Awards")
    var awards: String? = awards

    @ColumnInfo(name = "Poster")
    var poster: String? = poster

    @ColumnInfo(name = "Ratings")
    @TypeConverters()
    var ratings: List<Rating>? = ratings

    @ColumnInfo(name = "Metascore")
    var metascore: String? = metascore

    @ColumnInfo(name = "imdbRating")
    var imdbRating: String? = imdbRating

    @ColumnInfo(name = "imdbVotes")
    var imdbVotes: String? = imdbVotes

    @ColumnInfo(name = "imdbID")
    var imdbID: String? = imdbID

    @ColumnInfo(name = "Type")
    var type: String? = type

    @ColumnInfo(name = "DVD")
    var dvd: String? = dvd

    @ColumnInfo(name = "BoxOffice")
    var boxOffice: String? = boxOffice

    @ColumnInfo(name = "Production")
    var production: String? = production

    @ColumnInfo(name = "Website")
    var website: String? = website

    @ColumnInfo(name = "Response")
    var response: String? = response

}