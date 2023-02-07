package ru.job4j.cache;

public record Account(int id, int amount) {
    public static Account of(int id, int amount) {
        return new Account(id, amount);
    }

}
