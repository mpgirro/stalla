package dev.stalla.model;

import dev.stalla.builder.AtomBuilder;
import dev.stalla.builder.PersonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AtomBuiderFactoryTest {

    @Test
    @DisplayName("should build an Atom model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        PersonBuilder personBuilder = Person.builder()
            .name("name");

        AtomBuilder atomBuilder = Atom.builder()
            .addAuthorBuilder(personBuilder);

        assertNotNull(atomBuilder.build());
    }

}
