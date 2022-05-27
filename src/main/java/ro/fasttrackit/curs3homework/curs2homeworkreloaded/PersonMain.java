package ro.fasttrackit.curs3homework.curs2homeworkreloaded;

import ro.fasttrackit.curs3homework.curs2homeworkreloaded.impl.ReadFromFile;
import ro.fasttrackit.curs3homework.curs2homeworkreloaded.impl.ReadFromMemory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonMain {
    public static void main(String[] args) throws IOException {

        List<AgeRange> ageRanges = new ArrayList<>();
        ageRanges.add(new AgeRange(1, 30));
        ageRanges.add(new AgeRange(31, 50));
        ageRanges.add(null);

        PersonReportGenerator personReportGenerator = new PersonReportGenerator(getPersonProvider());
        personReportGenerator.setAgeRanges(ageRanges);
        personReportGenerator.generateReport("output-composition.txt");
    }

    private static PersonProvider getPersonProvider() {
        return System.currentTimeMillis() % 2 == 0
                ? new ReadFromMemory()
                : new ReadFromFile("src/main/resources/people.txt");
    }
}
