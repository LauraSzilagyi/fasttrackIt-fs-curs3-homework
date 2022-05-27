package ro.fasttrackit.curs3homework.ex2;

import java.time.Duration;
import java.util.Objects;

public final class Gym {
    private final GymMember gymMember;
    private Duration subscription;

    public Gym(GymMember gymMember, Duration subscription) {
        this.gymMember = gymMember;
        this.subscription = subscription;
    }

    public void setSubscription(Duration subscription) {
        this.subscription = subscription;
    }

    public GymMember getGymMember() {
        return gymMember;
    }

    public Duration getSubscription() {
        return subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return Objects.equals(gymMember, gym.gymMember) && Objects.equals(subscription, gym.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gymMember, subscription);
    }

    @Override
    public String toString() {
        return "Gym{" +
                "gymMember=" + gymMember +
                ", subscription=" + subscription +
                '}';
    }
}
