package ro.fasttrackit.curs2homeworkreloaded;

import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class PersonReportGenerator {
    private final PersonProvider personProvider;
    private final List<AgeRange> ageRanges = new ArrayList<>();


    public void setAgeRanges(List<AgeRange> ageRanges) {
        if (ageRanges != null) {
            List<AgeRange> sorted = ageRanges.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing(AgeRange::maxAge))
                    .toList();
            this.ageRanges.addAll(sorted);
        }
    }

    public void generateReport(String outputFile) throws IOException {
        List<Person> persons = personProvider.readPerson();
        generateOutputReport(persons, outputFile);
    }

    private void generateOutputReport(List<Person> persons, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            Map<String, List<String>> groupNames = persons.stream()
                    .collect(groupingBy(this::myGroupingLogic,
                            mapping(person -> person.name() + " " + person.firstname(), toList())));
            groupNames.remove("others");
            writeResult(groupNames, writer);
        }
    }

    private String myGroupingLogic(Person person) {
        return this.ageRanges.stream()
                .filter(isPersonAgeInAgeRange(person))
                .map(range -> range.minAge() + "-" + range.maxAge())
                .findFirst()
                .orElse("others");
    }

    private Predicate<AgeRange> isPersonAgeInAgeRange(Person person) {
        return range -> person.age() > range.minAge() && person.age() < range.maxAge();
    }

    private void writeResult(Map<String, List<String>> groupNames, BufferedWriter writer) {
        try {
            writer.write(String.valueOf(groupNames));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
