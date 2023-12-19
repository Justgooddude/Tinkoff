package edu.hw10.generator;

public class Student {
    private final int age;
    private final String name;

    public Student(
        @Min(21001) @Max(21999) int age,
        @NotNull String name
    ) {
        this.age = age;
        this.name = name;
    }

    public static Student create(
        @Min(21001) @Max(21999) int age,
        @NotNull String name
    ) {
        return new Student(age, name);
    }

    public int getgroup() {
        return age;
    }

    public String getName() {
        return name;
    }
}
