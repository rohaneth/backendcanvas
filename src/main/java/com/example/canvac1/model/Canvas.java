package com.example.canvac1.model;

import com.example.canvac1.model.drawable.CanvasObject;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "canvases")
public class Canvas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Date createdAt;

    @Column(columnDefinition = "TEXT")
    private String data;

    public Canvas() {
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public CanvasObject getCanvasObject() {
        return data != null ? CanvasObject.fromString(data) : new CanvasObject();
    }

    public void setCanvasObject(CanvasObject canvasObject) {
        this.data = canvasObject.toString();
    }
}
