package edu.hw10.generator;

public record RecStudent( @Min(21001) @Max(21999) int group,
                          @NotNull String name) {
}
