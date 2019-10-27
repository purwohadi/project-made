package com.purwohadi.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String judul;

    @SerializedName("poster_path")
    private String photo;

    @SerializedName("overview")
    private String description;

    @SerializedName("release_date")
    private String tanggalRilis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        dest.writeInt(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.photo);
        dest.writeString(this.description);
        dest.writeString(this.tanggalRilis);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.id = in.readInt();
        this.judul = in.readString();
        this.photo = in.readString();
        this.description = in.readString();
        this.tanggalRilis = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
