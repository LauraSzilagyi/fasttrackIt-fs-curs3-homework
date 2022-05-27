package ro.fasttrackit.curs3homework.curs2homeworkreloaded.impl;

import lombok.RequiredArgsConstructor;
import ro.fasttrackit.curs3homework.curs2homeworkreloaded.Person;
import ro.fasttrackit.curs3homework.curs2homeworkreloaded.PersonProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
public class ReadFromFile implements PersonProvider {

    private final String sourceFile;

    @Override
    public List<Person> readPerson() {
        try {
            return Files.lines(Path.of(sourceFile))
                    .map(this::toPerson)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Person toPerson(String line) {
        String[] tokens = line.split(",");
        return new Person(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
    }
}
