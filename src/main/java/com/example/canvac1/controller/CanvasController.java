package com.example.canvac1.controller;

import com.example.canvac1.model.Canvas;
import com.example.canvac1.model.drawable.CanvasObject;
import com.example.canvac1.model.drawable.DrawableObject;
import com.example.canvac1.service.CanvasService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/canvas")
public class CanvasController {
    private final CanvasService canvasService;

    public CanvasController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCanvas(@RequestBody Canvas canvas,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        canvas.setUsername(userDetails.getUsername());
        return ResponseEntity.ok("canvas/" + userDetails.getUsername() + "/" + canvas.getId());
    }
    @GetMapping("canvas/{username}")
    public ResponseEntity<CanvasObject> getCanvas(@PathVariable String username) {
        return ResponseEntity.ok(canvasService.getCanvasShapes(username));
    }
    
    @PostMapping("canvas/{username}/shapes")
    public ResponseEntity<CanvasObject> addShape(@PathVariable String username,
                                                @RequestBody DrawableObject shape) {
        CanvasObject canvasObject = canvasService.getCanvasShapes(username);
        canvasObject.getShapes().add(shape);
        canvasService.updateCanvasShapes(username, canvasObject);
        return ResponseEntity.ok(canvasObject);
    }

    @PutMapping("canvas/{username}/shapes")
    public ResponseEntity<CanvasObject> updateShapes(@PathVariable String username,
                                                   @RequestBody CanvasObject canvasObject) {
        canvasService.updateCanvasShapes(username, canvasObject);
        return ResponseEntity.ok(canvasObject);
    }
}
