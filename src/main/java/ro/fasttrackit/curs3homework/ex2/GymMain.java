package ro.fasttrackit.curs3homework.ex2;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class GymMain {
    public static void main(String[] args) throws IOException {
        GymMember laura = new GymMember("Laura", LocalDate.of(1995, 9, 23));
        GymMember mark = new GymMember("Mark", LocalDate.of(1995, 1, 2));
        GymMember maria = new GymMember("Maria", LocalDate.of(2003, 3, 14));
        GymMember ion = new GymMember("Ion", LocalDate.of(1986, 8, 21));
        GymMember dan = new GymMember("Dan", LocalDate.of(1995, 7, 6));

        GymService gymService = new GymService(List.of(
                new Gym(laura, Duration.ofHours(60)),
                new Gym(mark, Duration.ofHours(50)),
                new Gym(maria, Duration.ofHours(10)),
                new Gym(ion, Duration.ofHours(30)),
                new Gym(dan, Duration.ofHours(30))
        ));
//        register the time spent by a member
        System.out.println(gymService);
        gymService.registerTimeSpentByMember("Laura", Duration.ofHours(10));
        gymService.registerTimeSpentByMember("Mark", Duration.ofHours(10));
        gymService.registerTimeSpentByMember("Maria", Duration.ofHours(5));
        gymService.registerTimeSpentByMember("Ion", Duration.ofHours(30));
        gymService.registerTimeSpentByMember("Dan", Duration.ofHours(10));
        System.out.println(gymService);

//        the average, max, min age of members
        System.out.println("The average age of members: " + gymService.averageAgeOfMembers());
        System.out.println("The max age of members: " + gymService.maxAgeOfMembers());
        System.out.println("The min age of members: " + gymService.minAgeOfMembers());

//      the total remaining time for all members
        System.out.println(gymService.totalRemainingTime().toHours());

//        adding time to a member
        gymService.addTimeToAMember("mark", Duration.ofHours(5));
        System.out.println(gymService);

//      retrieving information about a member by their name
        Gym mark1 = gymService.retrieveInformationByMemberName("mark");
        System.out.println(mark1);

        gymService.generateReport("remaining-time-report-" + LocalDate.now() + ".txt");

    }
}
