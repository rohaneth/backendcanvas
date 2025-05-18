package com.example.canvac1.model.drawable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CircleShape extends DrawableObject {
    private int x;
    private int y;
    private int radius;
}
