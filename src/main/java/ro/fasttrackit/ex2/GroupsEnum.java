package ro.fasttrackit.ex2;

public enum GroupsEnum {
    RED(10),
    YELLOW(30),
    GREEN(Integer.MAX_VALUE);

    private final int timeLimit;

    GroupsEnum(int timeLimit){
        this.timeLimit = timeLimit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}
