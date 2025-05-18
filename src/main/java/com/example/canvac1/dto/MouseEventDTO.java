package com.example.canvac1.dto;

public class MouseEventDTO {
    private String eventType; // "mousedown", "mousemove", "mouseup"
    private double x;
    private double y;
    private String tool;      // e.g., "pen", "eraser"
    private String color;     // e.g., "#000000"
    private int strokeWidth;  // NEW: to handle dynamic stroke thickness
    private long timestamp;   // Optional: for ordering/replay

    public MouseEventDTO() {}

    public MouseEventDTO(String eventType, double x, double y, String tool, String color, int strokeWidth, long timestamp) {
        this.eventType = eventType;
        this.x = x;
        this.y = y;
        this.tool = tool;
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
