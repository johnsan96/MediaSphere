package com.john.backendMediaSphere.controller;

import com.john.backendMediaSphere.entity.Media;
import com.john.backendMediaSphere.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public List <Media> getAllMedia() {
        return mediaService.getAllMedia();
    }

    @PostMapping
    public Media addMedia(@RequestBody Media media) {
        return mediaService.addMedia(media);
    }

    @GetMapping("/{id}")
    public Media getMediaById(@PathVariable Long id) {
        return mediaService.getMediaById(id);
    }

    @PutMapping("/{id}")
    public Media updateMedia(@PathVariable Long id, @RequestBody Media media) {
        return mediaService.updateMedia(id, media);
    }

    @DeleteMapping("/{id}")
    public void deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
    }
}

