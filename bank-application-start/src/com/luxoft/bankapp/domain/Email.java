package com.luxoft.bankapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.luxoft.bankapp.domain.Client;

public class Email {
    private String from;
    private String to;
    private String title, body;
    public String getFrom() {
        return from;
    }

    public Email setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Email setTo(String to) {
        this.to = to;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Email setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Email setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return "SEND EMAIL:" + "\n" +
                "From: " + getFrom() +
                "To: " + getTo() +
                "Title: " + getTitle() + "\n" +
                "Body: " + getBody() + "\n";
    }
}