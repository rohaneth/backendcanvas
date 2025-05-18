package com.example.canvac1.model.drawable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RectangleShape.class, name = "rectangle"),
    @JsonSubTypes.Type(value = CircleShape.class, name = "circle"),
    @JsonSubTypes.Type(value = TriangleShape.class, name = "triangle"),
    @JsonSubTypes.Type(value = FreehandShape.class, name = "freehand"),
    @JsonSubTypes.Type(value = TextShape.class, name = "text")
})
public class CanvasObject {
    private List<DrawableObject> shapes = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public CanvasObject() {
    }

    public CanvasObject(List<DrawableObject> shapes) {
        this.shapes = shapes;
    }

    public void addShape(DrawableObject shape) {
        shapes.add(shape);
    }

    public void removeShape(DrawableObject shape) {
        shapes.remove(shape);
    }

    public static CanvasObject fromString(String json) {
        try {
            return objectMapper.readValue(json, CanvasObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON string to CanvasObject", e);
        }
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert CanvasObject to JSON string", e);
        }
    }
}
