package com.example.powerfitex;

public class Exercise {
    private String exID;
    private String bodyPart;
    private String exName;
    private String equipment;
    private String details;

    public Exercise() {
    }

    public Exercise(String exID, String bodyPart, String exName, String equipment, String details) {
        this.exID = exID;
        this.bodyPart = bodyPart;
        this.exName = exName;
        this.equipment = equipment;
        this.details = details;
    }

    public String getExID() {
        return exID;
    }

    public void setExID(String exID) {
        this.exID = exID;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    

}
