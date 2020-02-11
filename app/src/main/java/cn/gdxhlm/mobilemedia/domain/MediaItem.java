package cn.gdxhlm.mobilemedia.domain;

import java.io.Serializable;

public class MediaItem implements Serializable {
    private String name;
    private String sc;
    private String size;
    private String data;

    @Override
    public String toString() {
        return "MediaItem{" +
                "name='" + name + '\'' +
                ", sc='" + sc + '\'' +
                ", size='" + size + '\'' +
                ", data='" + data + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String artist;
}
