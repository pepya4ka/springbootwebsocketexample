package com.pepyachka.myspringbootwebsocketexample.model;

public class Message {

    private String from;
    private String text;

    public Message() {
    }

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String name) {
        this.from = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
