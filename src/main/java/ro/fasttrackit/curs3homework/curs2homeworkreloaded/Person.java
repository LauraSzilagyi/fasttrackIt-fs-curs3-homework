package ro.fasttrackit.curs3homework.curs2homeworkreloaded;

public record Person(String name, String firstname, int age) {

    public Person(String name, String firstname, int age) {
        this.name = name;
        this.firstname = firstname;
        this.age = Math.abs(age);
    }
}
