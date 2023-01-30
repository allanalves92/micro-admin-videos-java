package com.fullcycle.admin.catalogo.application.video.retrieve.get;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.domain.Fixture;
import com.fullcycle.admin.catalogo.domain.exceptions.NotFoundException;
import com.fullcycle.admin.catalogo.domain.utils.IdUtils;
import com.fullcycle.admin.catalogo.domain.video.AudioVideoMedia;
import com.fullcycle.admin.catalogo.domain.video.ImageMedia;
import com.fullcycle.admin.catalogo.domain.video.MediaStatus;
import com.fullcycle.admin.catalogo.domain.video.Video;
import com.fullcycle.admin.catalogo.domain.video.VideoGateway;
import com.fullcycle.admin.catalogo.domain.video.VideoID;
import com.fullcycle.admin.catalogo.domain.video.VideoMediaType;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class GetVideoByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetVideoByIdUseCase useCase;

    @Mock
    private VideoGateway videoGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(videoGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetVideo_shouldReturnIt() {
        // given
        //given
        final var expectedTitle = Fixture.title();
        final var expectedDescription = Fixture.Videos.description();
        final var expectedLaunchYear = Year.of(Fixture.year());
        final var expectedDuration = Fixture.duration();
        final var expectedOpened = Fixture.bool();
        final var expectedPublished = Fixture.bool();
        ;
        final var expectedRating = Fixture.Videos.rating();
        final var expectedCategories = Set.of(Fixture.Categories.aulas().getId());
        final var expectedGenres = Set.of(Fixture.Genres.tech().getId());
        final var expectedMembers = Set.of(
                Fixture.CastMembers.wesley().getId(),
                Fixture.CastMembers.gabriel().getId()
        );
        final var expectedVideo = audioVideo(VideoMediaType.VIDEO);
        final var expectedTrailer = audioVideo(VideoMediaType.TRAILER);
        final var expectedBanner = image(VideoMediaType.BANNER);
        final var expectedThumb = image(VideoMediaType.THUMBNAIL);
        final var expectedThumbHalf = image(VideoMediaType.THUMBNAIL_HALF);

        final var aVideo = Video.newVideo(
                        expectedTitle,
                        expectedDescription,
                        expectedLaunchYear,
                        expectedDuration,
                        expectedOpened,
                        expectedPublished,
                        expectedRating,
                        expectedCategories,
                        expectedGenres,
                        expectedMembers
                )
                .updateVideoMedia(expectedVideo)
                .updateTrailerMedia(expectedTrailer)
                .updateBannerMedia(expectedBanner)
                .updateThumbnailMedia(expectedThumb)
                .updateThumbnailHalfMedia(expectedThumbHalf);

        final var expectedId = aVideo.getId();

        when(videoGateway.findById(any())).thenReturn(Optional.of(Video.with(aVideo)));

        // when
        final var actualVideo = useCase.execute(expectedId.getValue());

        // then
        assertEquals(expectedId.getValue(), actualVideo.id());
        assertEquals(expectedTitle, actualVideo.title());
        assertEquals(expectedDescription, actualVideo.description());
        assertEquals(expectedLaunchYear.getValue(), actualVideo.launchedAt());
        assertEquals(expectedDuration, actualVideo.duration());
        assertEquals(expectedOpened, actualVideo.opened());
        assertEquals(expectedPublished, actualVideo.published());
        assertEquals(expectedRating, actualVideo.rating());
        assertEquals(asString(expectedCategories), actualVideo.categories());
        assertEquals(asString(expectedGenres), actualVideo.genres());
        assertEquals(asString(expectedMembers), actualVideo.castMembers());
        assertEquals(expectedVideo, actualVideo.video());
        assertEquals(expectedTrailer, actualVideo.trailer());
        assertEquals(expectedBanner, actualVideo.banner());
        assertEquals(expectedThumb, actualVideo.thumbnail());
        assertEquals(expectedThumbHalf, actualVideo.thumbnailHalf());
        assertEquals(aVideo.getCreatedAt(), actualVideo.createdAt());
        assertEquals(aVideo.getUpdatedAt(), actualVideo.updatedAt());

    }

    @Test
    public void givenAnInvalidId_whenCallsGetVideo_shouldReturnNotFound() {
        // given
        final var expectedErrorMessage = "Video with ID 123 was not found";

        final var expectedId = VideoID.from("123");

        when(videoGateway.findById(eq(expectedId)))
                .thenReturn(Optional.empty());

        // when
        final var actualException = assertThrows(NotFoundException.class, () -> {
            useCase.execute(expectedId.getValue());
        });

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    private AudioVideoMedia audioVideo(final VideoMediaType type) {
        final var checksum = IdUtils.uuid();
        return AudioVideoMedia.with(
                checksum,
                checksum,
                type.name().toLowerCase(),
                "/videos/" + checksum,
                "",
                MediaStatus.PENDING
        );
    }

    private ImageMedia image(final VideoMediaType type) {
        final var checksum = IdUtils.uuid();
        return ImageMedia.with(
                checksum,
                type.name().toLowerCase(),
                "/images/" + checksum
        );
    }

}