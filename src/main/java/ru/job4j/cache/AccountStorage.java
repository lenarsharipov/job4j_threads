package ru.job4j.cache;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public final class AccountStorage {
    @GuardedBy("accounts")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    @GuardedBy("accounts")
    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(
                account.id(), Account.of(account.id(), account.amount())) == null;
    }

    @GuardedBy("accounts")
    public synchronized boolean update(Account account) {
        return accounts.replace(
                account.id(),
                accounts.get(account.id()),
                Account.of(account.id(), account.amount()));
    }

    @GuardedBy("accounts")
    public synchronized boolean delete(int id) {
        return accounts.remove(id, accounts.get(id));
    }

    @GuardedBy("accounts")
    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @GuardedBy("accounts")
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var rsl = false;
        var source = getById(fromId);
        var target = getById(toId);
        if (source.isPresent() && target.isPresent() && source.get().amount() >= amount) {
            accounts.put(toId, Account.of(toId, accounts.get(toId).amount() + amount));
            accounts.put(fromId, Account.of(fromId, accounts.get(fromId).amount() - amount));
            rsl = true;
        }
        return rsl;
    }
}