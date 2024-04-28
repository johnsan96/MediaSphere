package com.john.backendMediaSphere.service;

import com.john.backendMediaSphere.entity.Media;
import com.john.backendMediaSphere.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService { 

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List <Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    public Media addMedia(Media media) {
        return mediaRepository.save(media);
    }

    public Media getMediaById(Long id) {
        Optional <Media> optionalMedia = mediaRepository.findById(id);
        return optionalMedia.orElse(null);
    }

    public Media updateMedia(Long id, Media updatedMedia) {
        Optional <Media> optionalMedia = mediaRepository.findById(id);
        if (optionalMedia.isPresent()) {
            Media media = optionalMedia.get();
            media.setTitle(updatedMedia.getTitle());
            media.setDescription(updatedMedia.getDescription());
            media.setType(updatedMedia.getType());
            return mediaRepository.save(media);
        } else {
            return null;
        }
    }

    public void deleteMedia(Long id) {
        mediaRepository.deleteById(id);
    }
}

