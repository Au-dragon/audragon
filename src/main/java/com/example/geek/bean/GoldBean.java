package com.example.geek.bean;

import java.io.Serializable;

public class GoldBean implements Serializable {
    private String title;
    private boolean isChecked;

    public GoldBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "GoldBean{" +
                "title='" + title + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
