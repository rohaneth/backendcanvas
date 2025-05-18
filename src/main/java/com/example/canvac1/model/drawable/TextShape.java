package com.example.canvac1.model.drawable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextShape extends DrawableObject {
    private int x;
    private int y;
    private String text;
    private int fontSize;
    private String fontFamily;
}
