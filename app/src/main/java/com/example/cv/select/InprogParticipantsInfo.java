package com.example.cv.select;

import android.provider.BaseColumns;

public class InprogParticipantsInfo {

    private String participantName;
    private String participantContact;

    public InprogParticipantsInfo() {
    }


    public InprogParticipantsInfo(String participantName, String participantContact) {
        this.participantName = participantName;
        this.participantContact = participantContact;
    }


    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantContact() {
        return participantContact;
    }

    public void setParticipantContact(String participantContact) {
        this.participantContact = participantContact;
    }
}
