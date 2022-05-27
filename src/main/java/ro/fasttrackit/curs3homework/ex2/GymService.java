package ro.fasttrackit.curs3homework.ex2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class GymService {

    public final List<Gym> gymList;

    public GymService(List<Gym> gymList) {
        this.gymList = new ArrayList<>(gymList);
    }

    public void registerTimeSpentByMember(String name, Duration timeSpent) {
        Gym memberByName = retrieveInformationByMemberName(name);
        Duration remainingTime = memberByName.getSubscription().minus(timeSpent);
        if (remainingTime.isNegative()) {
            memberByName.setSubscription(Duration.ofHours(0));
        } else {
            memberByName.setSubscription(remainingTime);
        }
    }

    public double averageAgeOfMembers() {
        return this.gymList.stream()
                .mapToInt(gym -> gym.getGymMember().getAge())
                .average()
                .orElse(0);
    }

    public int maxAgeOfMembers() {
        return this.gymList.stream()
                .mapToInt(gym -> gym.getGymMember().getAge())
                .max()
                .orElse(0);
    }

    public int minAgeOfMembers() {
        return this.gymList.stream()
                .mapToInt(gym -> gym.getGymMember().getAge())
                .min()
                .orElse(0);
    }

    public Duration totalRemainingTime() {
        long sum = this.gymList.stream()
                .mapToLong(gym -> gym.getSubscription().toSeconds())
                .sum();
        return Duration.ofSeconds(sum);
    }

    public void addTimeToAMember(String name, Duration plusTime) {
        Gym memberByName = retrieveInformationByMemberName(name);
        memberByName.setSubscription(memberByName.getSubscription().plus(plusTime));
    }

    public Gym retrieveInformationByMemberName(String name) {
        return this.gymList.stream()
                .filter(g -> g.getGymMember().name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Member not found!"));
    }

    public void generateReport(String outputFile) throws IOException {
        generateOutputReport(outputFile);
    }

    private void generateOutputReport(String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            Map<String, List<Gym>> result = getMembersByGroup();
            Arrays.stream(GroupsEnum.values())
                    .forEach(groupsEnum -> writeResultByGroup(writer, result, groupsEnum));
        }
    }

    private void writeResultByGroup(BufferedWriter writer, Map<String, List<Gym>> result, GroupsEnum groupsEnum) {
        String names = getNamesSeparatedByComma(result, groupsEnum);
        writeResult(writer, groupsEnum.name() + ": ");
        writeResult(writer, names);
        writeNewLine(writer);
    }

    private String getNamesSeparatedByComma(Map<String, List<Gym>> result, GroupsEnum groupsEnum) {
        return result.getOrDefault(groupsEnum.name(), new ArrayList<>())
                .stream()
                .map(gym -> gym.getGymMember().name())
                .collect(joining(", "));
    }

    private Map<String, List<Gym>> getMembersByGroup() {
        return this.gymList.stream()
                .collect(groupingBy(this::groupingLogic));
    }

    private String groupingLogic(Gym gym) {
        return Arrays.stream(GroupsEnum.values())
                .filter(groupsEnum -> groupsEnum.getTimeLimit() > gym.getSubscription().toHours())
                .map(Enum::name)
                .findFirst()
                .orElse("other");
    }

    private void writeResult(BufferedWriter writer, String value) {
        try {
            writer.write(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeNewLine(BufferedWriter writer) {
        try {
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "GymService{" +
                "gymList=" + gymList +
                '}';
    }
}
