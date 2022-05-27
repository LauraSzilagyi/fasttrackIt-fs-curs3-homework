package ro.fasttrackit.curs3homework.ex2;

import java.time.LocalDate;
import java.time.Period;

public record GymMember(String name, LocalDate birthdate) {

    public int getAge(){
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthdate, now);
        return age.getYears();
    }
}
