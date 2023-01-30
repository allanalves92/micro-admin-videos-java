package com.fullcycle.admin.catalogo.domain.video;

import java.util.Optional;

public interface MediaResourceGateway {

    AudioVideoMedia storeAudioVideo(VideoID andId, VideoResource aResource);

    ImageMedia storeImage(VideoID andId, VideoResource aResource);

    Optional<Resource> getResource(VideoID anId, VideoMediaType type);

    void clearResources(VideoID anId);

}
