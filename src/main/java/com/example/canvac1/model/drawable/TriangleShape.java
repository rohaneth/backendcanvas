package com.example.canvac1.model.drawable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TriangleShape extends DrawableObject {
    private Point point1;
    private Point point2;
    private Point point3;
}
