package com.jimmy.net_sample.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "note",strict = false)
public class Note {
    @Element(name = "from")
    private String from;
    @Element(name = "to")
    private String to;
    @Element(name = "message")
    private String message;

    public Note() {
    }

    public Note(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String getFrom() {
        return from == null ? "" : from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to == null ? "" : to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Note{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}