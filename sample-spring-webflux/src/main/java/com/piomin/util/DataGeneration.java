package com.piomin.util;

import com.piomin.model.Person;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DataGeneration {
    public Stream<Person> prepareStream() {
        return Stream.of(
                new Person(1, "Name01", "Surname01", 11),
                new Person(2, "Name02", "Surname02", 22),
                new Person(3, "Name03", "Surname03", 33),
                new Person(4, "Name04", "Surname04", 44),
                new Person(5, "Name05", "Surname05", 55),
                new Person(6, "Name06", "Surname06", 66),
                new Person(7, "Name07", "Surname07", 77),
                new Person(8, "Name08", "Surname08", 88),
                new Person(9, "Name09", "Surname09", 99)
        );
    }

    public Stream<Person> prepareStreamPart1() {
        return Stream.of(
                new Person(1, "Name01", "Surname01", 11),
                new Person(2, "Name02", "Surname02", 22),
                new Person(3, "Name03", "Surname03", 33)
        );
    }
}