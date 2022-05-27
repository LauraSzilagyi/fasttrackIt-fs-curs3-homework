package ro.fasttrackit.curs3homework.curs2homeworkreloaded.impl;
import ro.fasttrackit.curs3homework.curs2homeworkreloaded.Person;
import ro.fasttrackit.curs3homework.curs2homeworkreloaded.PersonProvider;

import java.util.List;

public class ReadFromMemory implements PersonProvider {
    @Override
    public List<Person> readPerson() {
        return List.of(
                new Person("Delena", "Spitler", 23),
                new Person("Leo", "Paschal", 33),
                new Person("Reena", "Stach", 12)
        );
    }
}