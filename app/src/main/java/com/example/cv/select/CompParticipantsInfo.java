package com.example.cv.select;

import android.provider.BaseColumns;

public class CompParticipantsInfo {
    private String participantName;
    private String participantContact;
    private String participantEnroll;

    public CompParticipantsInfo() {
    }

    public CompParticipantsInfo(String participantName, String participantContact, String participantEnroll) {
        this.participantName = participantName;
        this.participantContact = participantContact;
        this.participantEnroll = participantEnroll;
    }

    public static final class participantsInfo implements BaseColumns {
        public static final String TABLE_NAME = "patient";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_Contact = "ContactSim";
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

    public String getParticipantEnroll() {
        return participantEnroll;
    }

    public void setParticipantEnroll(String participantEnroll) {
        this.participantEnroll = participantEnroll;
    }
}
