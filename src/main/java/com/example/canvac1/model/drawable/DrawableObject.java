package com.example.canvac1.model.drawable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RectangleShape.class, name = "rectangle"),
    @JsonSubTypes.Type(value = CircleShape.class, name = "circle"),
    @JsonSubTypes.Type(value = TriangleShape.class, name = "triangle"),
    @JsonSubTypes.Type(value = FreehandShape.class, name = "freehand"),
    @JsonSubTypes.Type(value = TextShape.class, name = "text")
})
public abstract class DrawableObject {
    private String id;
    private String color;
    private int strokeWidth;
    private String type;
}
