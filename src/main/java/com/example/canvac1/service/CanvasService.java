package com.example.canvac1.service;

import com.example.canvac1.model.Canvas;
import com.example.canvac1.model.drawable.CanvasObject;
import com.example.canvac1.repository.CanvasRepository;
import org.springframework.stereotype.Service;


@Service
public class CanvasService {
    private final CanvasRepository canvasRepository;

    public CanvasService(CanvasRepository canvasRepository) {
        this.canvasRepository = canvasRepository;
    }

    public Canvas createCanvas(Canvas canvas) {
        return canvasRepository.save(canvas);
    }

    public Canvas getCanvas(String username) {
        // Get the user's canvas (assuming one canvas per user for now)
        // You might want to update this if you need to support multiple canvases per user
        return canvasRepository.findByUsername(username).stream().findFirst().orElse(null);
    }

    public Canvas getOrCreateCanvas(String username) {
        Canvas canvas = getCanvas(username);
        if (canvas == null) {
            canvas = new Canvas();
            canvas.setUsername(username);
            canvasRepository.save(canvas);
        }
        return canvas;
    }
    public Canvas updateCanvasShapes(String username, CanvasObject canvasObject) {
        Canvas canvas = getCanvas(username);
        if (canvas == null) {
            // Create a new canvas if it doesn't exist
            canvas = new Canvas();
            canvas.setUsername(username);
        }
        canvas.setData(canvasObject.toString());
        return canvasRepository.save(canvas);
    }

    public CanvasObject getCanvasShapes(String username) {
        Canvas canvas = getCanvas(username);
        if (canvas != null && canvas.getData() != null) {
            try {
                return new CanvasObject(); // TODO: Implement proper deserialization
            } catch (Exception e) {
                throw new RuntimeException("Error parsing canvas data", e);
            }
        }
        return new CanvasObject();
    }
}
