package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.EmailException;

public class EmailService implements Runnable {

    private Queue queue = new Queue();
    private boolean closed;
    private int sentEmails = 0;

    public EmailService() {
        new Thread(this).start();
    }
    public void printQueue() {
        queue.printQueue();
    }
    @Override
    public void run() {
        Email email;
        while (true) {
            if(closed) {
                return;
            }

            if ((email = queue.get()) != null) {
                sendEmail(email);
            }
            try {
                synchronized(queue) {
                    queue.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

        }
    }

    public int getSentEmails() {
        return sentEmails;
    }

    private void sendEmail(Email email) {
        System.out.println(email);
        sentEmails++;
    }

    public void sendNotificationEmail(Email email) throws EmailException {
        if (!closed) {
            queue.add(email);
            synchronized(queue) {
                queue.notify();
            }
        } else
            throw new EmailException("Mailbox is closed!");
    }

    public void sendEmailsBeforeClose() {
        while (queue.get() != null) {
            sendEmail(queue.get());
        }
    }
    public void close() {
        closed = true;
        // sendEmailsBeforeClose();
        synchronized(queue) {
            queue.notify();
        }
    }
}
