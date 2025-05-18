package com.example.canvac1.handler;

import com.example.canvac1.model.drawable.CanvasObject;
import com.example.canvac1.model.drawable.DrawableObject;
import com.example.canvac1.model.message.ShapeEraseMessage;
import com.example.canvac1.service.CanvasService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.example.canvac1.dto.MouseEventDTO;

@Controller
public class CanvasWebSocketHandler {

    private final CanvasService canvasService;

    public CanvasWebSocketHandler(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @MessageMapping("/canvas/{username}/addShape")
    @SendTo("/topic/canvas/{username}/updates")
    public MouseEventDTO addShape(@Payload MouseEventDTO event) {
       // System.out.println(event);
        return event;
    }

    @MessageMapping("/canvas/{username}/eraseShape")
    @SendTo("/topic/canvas/{username}/updates")
    public MouseEventDTO eraseShape(@Payload MouseEventDTO event,
                                  @DestinationVariable String username) {
        //System.out.println(event);
        return event;
    }

    



}
