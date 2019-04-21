package com.example.geek.bean;

public class V2ListBean {
    private String img;
    private String commentCount;
    private String title;
    private String topic;
    private String text1;
    private String author;
    private String commentPeople;

    public V2ListBean(String img, String commentCount, String title, String topic, String text1, String author, String commentPeople) {
        this.img = img;
        this.commentCount = commentCount;
        this.title = title;
        this.topic = topic;
        this.text1 = text1;
        this.author = author;
        this.commentPeople = commentPeople;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommentPeople() {
        return commentPeople;
    }

    public void setCommentPeople(String commentPeople) {
        this.commentPeople = commentPeople;
    }

    @Override
    public String toString() {
        return "V2ListBean{" +
                "img='" + img + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", text1='" + text1 + '\'' +
                ", author='" + author + '\'' +
                ", commentPeople='" + commentPeople + '\'' +
                '}';
    }
}
