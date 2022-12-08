package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.domain.Email;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Queue {

    private List<Email> emails = Collections.synchronizedList(new LinkedList<>());

    public void add(Email email) {
        emails.add(email);
    }

    public Email get() {
        if (emails.size() > 0) {
            return emails.remove(emails.size() - 1);
        }
        return null;
    }

    public void printQueue() {
        for (Email email : emails) {
            System.out.println(email);
        }
        System.out.println();
    }

}
