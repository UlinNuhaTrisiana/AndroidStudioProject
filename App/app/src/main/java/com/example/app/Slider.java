package com.example.app;

public class Slider {
    private int img;
    private String name;

    public Slider(int image, String nama){
        img = image;
        name = nama;

    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setImg(int image) {
        img = image;
    }

    public void setName(String nama) {
        name = nama;
    }
}
