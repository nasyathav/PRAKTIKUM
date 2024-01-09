package com.example.crudfirebase;

import android.os.Parcel;
import android.os.Parcelable;

public class Mhs implements Parcelable {

    String key;
    String nama;
    String nim;
    String noHp;

    public Mhs(String key, String nama, String nim, String noHp) {
        this.key = key;
        this.nama = nama;
        this.nim = nim;
        this.noHp = noHp;
    }

    public Mhs( String nama, String nim, String noHp) {
        this.nama = nama;
        this.nim = nim;
        this.noHp = noHp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.nama);
        dest.writeString(this.nim);
        dest.writeString(this.noHp);
    }

    public void readFromParcel(Parcel source) {
        this.key = source.readString();
        this.nama = source.readString();
        this.nim = source.readString();
        this.noHp = source.readString();
    }

    protected Mhs(Parcel in) {
        this.key = in.readString();
        this.nama = in.readString();
        this.nim = in.readString();
        this.noHp = in.readString();
    }

    public static final Creator<Mhs> CREATOR = new Creator<Mhs>() {
        @Override
        public Mhs createFromParcel(Parcel source) {
            return new Mhs(source);
        }

        @Override
        public Mhs[] newArray(int size) {
            return new Mhs[size];
        }
    };
}