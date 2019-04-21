package com.example.geek.bean;

import java.io.Serializable;

public class V2Bean {
    private String link;
    private String tab;

    public V2Bean(String link, String tab) {
        this.link = link;
        this.tab = tab;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    @Override
    public String toString() {
        return "V2Bean{" +
                "link='" + link + '\'' +
                ", tab='" + tab + '\'' +
                '}';
    }
}
