package com.fullcycle.admin.catalogo.application.castmember.create;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberGateway;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberType;
import com.fullcycle.admin.catalogo.domain.exceptions.NotificationException;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static com.fullcycle.admin.catalogo.domain.Fixture.CastMembers;
import static com.fullcycle.admin.catalogo.domain.Fixture.name;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateCastMemberUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateCastMemberUseCase useCase;

    @Mock
    private CastMemberGateway castMemberGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(castMemberGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateCastMember_shouldReturnIt() {
        //given
        final var expectedName = name();
        final var expectedType = CastMembers.type();

        final var aCommand = CreateCastMemberCommand.with(expectedName, expectedType);

        when(castMemberGateway.create(any())).thenAnswer(returnsFirstArg());

        //when
        final var actualOutput = useCase.execute(aCommand);

        //then
        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());

        verify(castMemberGateway, times(1)).create(argThat(aMember ->
                Objects.nonNull(aMember.getId()) &&
                        Objects.equals(expectedName, aMember.getName()) &&
                        Objects.equals(expectedType, aMember.getType()) &&
                        Objects.nonNull(aMember.getCreatedAt()) &&
                        Objects.nonNull(aMember.getUpdatedAt())
        ));
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateCastMember_shouldThrowsNotificationException() {
        //given
        final String expectedName = null;
        final var expectedType = CastMembers.type();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var aCommand = CreateCastMemberCommand.with(expectedName, expectedType);

        //when
        final var actualException = assertThrows(NotificationException.class, () -> {
            useCase.execute(aCommand);
        });

        //then
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

        verify(castMemberGateway, times(0)).create(any());

    }

    @Test
    public void givenAnInvalidType_whenCallsCreateCastMember_shouldThrowsNotificationException() {
        //given
        final var expectedName = name();
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        final var aCommand = CreateCastMemberCommand.with(expectedName, expectedType);

        //when
        final var actualException = assertThrows(NotificationException.class, () -> {
            useCase.execute(aCommand);
        });

        //then
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

        verify(castMemberGateway, times(0)).create(any());

    }
}
