package com.example.canvac1.model.message;

public class ShapeEraseMessage {
    private String id;
    private String type;

    public ShapeEraseMessage() {}
    
    public ShapeEraseMessage(String id) {
        this.id = id;
        this.type = "ERASE";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
