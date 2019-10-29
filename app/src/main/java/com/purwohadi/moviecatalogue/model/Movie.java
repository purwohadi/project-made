package com.purwohadi.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class Movie implements Parcelable {
    private int id;
    private String judul, photo,description, tanggalRilis;

    public Movie(JSONObject object) {
        try {
            Integer id = object.getInt("id");
            String photo = object.getString("poster_path");
            String judul = object.getString("title");
            String description = object.getString("overview");
            String tanggalRilis = object.getString("release_date");

            this.id = id;
            this.photo=photo;
            this.judul=judul;
            this.description=description;
            this.tanggalRilis=tanggalRilis;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTanggalRilis() {
        return tanggalRilis;
    }

    public void setTanggalRilis(String tanggalRilis) {
        this.tanggalRilis = tanggalRilis;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.judul);
        dest.writeString(this.description);
        dest.writeString(this.tanggalRilis);
    }


    protected Movie(Parcel in) {
        this.photo = in.readString();
        this.judul = in.readString();
        this.description = in.readString();
        this.tanggalRilis = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
