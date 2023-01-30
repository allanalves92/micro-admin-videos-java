package com.fullcycle.admin.catalogo.domain.video;

import com.fullcycle.admin.catalogo.domain.UnitTest;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberID;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.genre.GenreID;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import java.time.Year;
import java.util.Set;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VideoValidatorTest extends UnitTest {

    @Test
    public void givenNullTitle_whenCallsValidate_shouldReceiveError() {
        //given
        final String expectedTitle = null;
        final var expectedDescription = """
                Disclaimer: o estudo de caso apresentado tem fins educacionas e representa nossas opiniõe pessoais.
                Esse video faz parte da Imersão Full Stack && Full Cycle
                Para acessar todas as aulas, lives e desafios, acesse:
                https://imersao.fullcycle.com.br/
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be null";
        final var actualVideo =
                Video.newVideo(expectedTitle, expectedDescription, expectedLaunchedAt, expectedDuration, expectedOpened,
                        expectedPublished, expectedRating, expectedCategories, expectedGenres, expectedMembers);

        //when
        final var actualError =
                assertThrows(DomainException.class, () -> actualVideo.validate(new ThrowsValidationHandler()));

        //then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenEmptyTitle_whenCallsValidate_shouldReceiveError() {
        //given
        final var expectedTitle = "";
        final var expectedDescription = """
                Disclaimer: o estudo de caso apresentado tem fins educacionas e representa nossas opiniõe pessoais.
                Esse video faz parte da Imersão Full Stack && Full Cycle
                Para acessar todas as aulas, lives e desafios, acesse:
                https://imersao.fullcycle.com.br/
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be empty";
        final var actualVideo =
                Video.newVideo(expectedTitle, expectedDescription, expectedLaunchedAt, expectedDuration, expectedOpened,
                        expectedPublished, expectedRating, expectedCategories, expectedGenres, expectedMembers);

        //when
        final var actualError =
                assertThrows(DomainException.class, () -> actualVideo.validate(new ThrowsValidationHandler()));

        //then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenTitleWithLengthGreaterThan255_whenCallsValidate_shouldReceiveError() {
        //given
        final var expectedTitle = """
                             Disclaimer: o estudo de caso apresentado tem fins educacionas e representa nossas opiniõe pessoais.
                        Esse video faz parte da Imersão Full Stack && Full Cycle
                        Esse video faz parte da Imersão Full Stack && Full Cycle
                        Para acessar todas as aulas, lives e desafios, acesse:
                        https://imersao.fullcycle.com.br/
                """;

        final var expectedDescription = """
                        Disclaimer: o estudo de caso apresentado tem fins educacionas e representa nossas opiniõe pessoais.
                        Esse video faz parte da Imersão Full Stack && Full Cycle
                        Para acessar todas as aulas, lives e desafios, acesse:
                        https://imersao.fullcycle.com.br/
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' must be between 1 and 255 characters";
        final var actualVideo =
                Video.newVideo(expectedTitle, expectedDescription, expectedLaunchedAt, expectedDuration, expectedOpened,
                        expectedPublished, expectedRating, expectedCategories, expectedGenres, expectedMembers);

        //when
        final var actualError =
                assertThrows(DomainException.class, () -> actualVideo.validate(new ThrowsValidationHandler()));

        //then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenEmptyDescription_whenCallsValidate_shouldReceiveError() {
        //given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = "";
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";
        final var actualVideo =
                Video.newVideo(expectedTitle, expectedDescription, expectedLaunchedAt, expectedDuration, expectedOpened,
                        expectedPublished, expectedRating, expectedCategories, expectedGenres, expectedMembers);

        //when
        final var actualError =
                assertThrows(DomainException.class, () -> actualVideo.validate(new ThrowsValidationHandler()));

        //then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenDescriptionWithLengthGreaterThan4000_whenCallsValidate_shouldReceiveError() {
        //given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = """
                                  O cuidado em identificar pontos críticos na execução dos pontos do programa garante a contribuição de um grupo importante na determinação das condições financeiras e administrativas exigidas. A nível organizacional, o acompanhamento das preferências de consumo cumpre um papel essencial na formulação das novas proposições. As experiências acumuladas demonstram que a contínua expansão de nossa atividade obstaculiza a apreciação da importância dos conhecimentos estratégicos para atingir a excelência. Por conseguinte, a mobilidade dos capitais internacionais exige a precisão e a definição das posturas dos órgãos dirigentes com relação às suas atribuições.

                        A certificação de metodologias que nos auxiliam a lidar com o desafiador cenário globalizado ainda não demonstrou convincentemente que vai participar na mudança do orçamento setorial. Podemos já vislumbrar o modo pelo qual a estrutura atual da organização causa impacto indireto na reavaliação dos modos de operação convencionais. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que a constante divulgação das informações estende o alcance e a importância dos níveis de motivação departamental. Pensando mais a longo prazo, a necessidade de renovação processual prepara-nos para enfrentar situações atípicas decorrentes das condições inegavelmente apropriadas. Evidentemente, a consolidação das estruturas é uma das consequências do investimento em reciclagem técnica.

                        No entanto, não podemos esquecer que a hegemonia do ambiente político oferece uma interessante oportunidade para verificação do sistema de participação geral. Não obstante, a complexidade dos estudos efetuados pode nos levar a considerar a reestruturação do processo de comunicação como um todo. Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se a consulta aos diversos militantes talvez venha a ressaltar a relatividade dos relacionamentos verticais entre as hierarquias. É claro que o fenômeno da Internet estimula a padronização do fluxo de informações.

                        Percebemos, cada vez mais, que o início da atividade geral de formação de atitudes não pode mais se dissociar dos métodos utilizados na avaliação de resultados. Ainda assim, existem dúvidas a respeito de como a expansão dos mercados mundiais afeta positivamente a correta previsão dos paradigmas corporativos. Desta maneira, a valorização de fatores subjetivos maximiza as possibilidades por conta das formas de ação. Todavia, a competitividade nas transações comerciais desafia a capacidade de equalização da gestão inovadora da qual fazemos parte. Por outro lado, a percepção das dificuldades agrega valor ao estabelecimento dos procedimentos normalmente adotados.

                        Acima de tudo, é fundamental ressaltar que a crescente influência da mídia promove a alavancagem de alternativas às soluções ortodoxas. Assim mesmo, o surgimento do comércio virtual acarreta um processo de reformulação e modernização de todos os recursos funcionais envolvidos. No mundo atual, a revolução dos costumes representa uma abertura para a melhoria das regras de conduta normativas. Gostaria de enfatizar que o entendimento das metas propostas possibilita uma melhor visão global do impacto na agilidade decisória.
                        
                         Acima de tudo, é fundamental ressaltar que a crescente influência da mídia promove a alavancagem de alternativas às soluções ortodoxas. Assim mesmo, o surgimento do comércio virtual acarreta um processo de reformulação e modernização de todos os recursos funcionais envolvidos. No mundo atual, a revolução dos costumes representa uma abertura para a melhoria das regras de conduta normativas. Gostaria de enfatizar que o entendimento das metas propostas possibilita uma melhor visão global do impacto na agilidade decisória.
                         
                          Acima de tudo, é fundamental ressaltar que a crescente influência da mídia promove a alavancagem de alternativas às soluções ortodoxas. Assim mesmo, o surgimento do comércio virtual acarreta um processo de reformulação.
                """;

        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 1 and 4000 characters";

        final var actualVideo =
                Video.newVideo(expectedTitle, expectedDescription, expectedLaunchedAt, expectedDuration, expectedOpened,
                        expectedPublished, expectedRating, expectedCategories, expectedGenres, expectedMembers);

        //when
        final var actualError =
                assertThrows(DomainException.class, () -> actualVideo.validate(new ThrowsValidationHandler()));

        //then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenNullLaunchedAt_whenCallsValidate_shouldReceiveError() {
        //given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = "A description";
        final Year expectedLaunchedAt = null;
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'launchedAt' should not be null";
        final var actualVideo =
                Video.newVideo(expectedTitle, expectedDescription, expectedLaunchedAt, expectedDuration, expectedOpened,
                        expectedPublished, expectedRating, expectedCategories, expectedGenres, expectedMembers);

        //when
        final var actualError =
                assertThrows(DomainException.class, () -> actualVideo.validate(new ThrowsValidationHandler()));

        //then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    public void givenNullRating_whenCallsValidate_shouldReceiveError() {
        //given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = "A description";
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final Rating expectedRating = null;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'rating' should not be null";

        final var actualVideo =
                Video.newVideo(expectedTitle, expectedDescription, expectedLaunchedAt, expectedDuration, expectedOpened,
                        expectedPublished, expectedRating, expectedCategories, expectedGenres, expectedMembers);


        //when
        final var actualError =
                assertThrows(DomainException.class, () -> actualVideo.validate(new ThrowsValidationHandler()));

        //then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }
}
