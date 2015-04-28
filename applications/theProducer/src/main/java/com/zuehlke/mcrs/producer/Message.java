package com.zuehlke.mcrs.producer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

/**
 * Created by kinggrass on 08.04.15.
 */
public class Message {

    private final String text;
    private final String number;
    private final DateTime creationDate;

    public Message(String text, String number) {
        this.text = text;
        this.number = number;
        this.creationDate = DateTime.now();
    }

    public String getText() {
        return text;
    }

    public String getNumber() {
        return number;
    }

    @JsonIgnore
    public DateTime getCreationDate() {
        return creationDate;
    }


    public String getCreationDateString() {
        return creationDate.toString();
    }



    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", number='" + number + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
