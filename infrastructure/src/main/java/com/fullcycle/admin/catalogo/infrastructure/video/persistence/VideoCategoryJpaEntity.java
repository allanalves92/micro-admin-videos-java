package com.fullcycle.admin.catalogo.infrastructure.video.persistence;

import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Table(name = "videos_categories")
@Entity(name = "VideoCategory")
public class VideoCategoryJpaEntity {

    @EmbeddedId
    private VideoCategoryID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videoId")
    private VideoJpaEntity video;

    public VideoCategoryJpaEntity() {
    }

    private VideoCategoryJpaEntity(final VideoCategoryID id, final VideoJpaEntity video) {
        this.id = id;
        this.video = video;
    }

    public static VideoCategoryJpaEntity from(final VideoJpaEntity video, final CategoryID category) {
        return new VideoCategoryJpaEntity(VideoCategoryID.from(video.getId(), category.getValue()), video);
    }

    public VideoCategoryID getId() {
        return id;
    }

    public void setId(VideoCategoryID id) {
        this.id = id;
    }

    public VideoJpaEntity getVideo() {
        return video;
    }

    public void setVideo(VideoJpaEntity video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoCategoryJpaEntity)) {
            return false;
        }
        VideoCategoryJpaEntity that = (VideoCategoryJpaEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getVideo(), that.getVideo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVideo());
    }
}
