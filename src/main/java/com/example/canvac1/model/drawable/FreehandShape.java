package com.example.canvac1.model.drawable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FreehandShape extends DrawableObject {
    private List<Point> points;
}
