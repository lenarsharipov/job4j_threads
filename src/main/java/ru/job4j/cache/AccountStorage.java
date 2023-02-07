package ru.job4j.cache;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public final class AccountStorage {
    @GuardedBy("accounts")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public boolean add(Account account) {
        synchronized (accounts) {
            var rsl = false;
            if (accounts.get(account.id()) == null) {
                var tempAccount = Account.of(account.id(), account.amount());
                accounts.put(account.id(), tempAccount);
                rsl = true;
            }
            return rsl;
        }
    }

    public boolean update(Account account) {
        synchronized (accounts) {
            var rsl = false;
            if (accounts.get(account.id()) != null) {
                accounts.put(account.id(), Account.of(account.id(), account.amount()));
                rsl = true;
            }
            return rsl;
        }
    }

    public boolean delete(int id) {
        synchronized (accounts) {
            return accounts.remove(id, accounts.get(id));
        }
    }

    public Optional<Account> getById(int id) {
        synchronized (accounts) {
            var rsl = accounts.get(id);
            if (rsl != null) {
                rsl = Account.of(id, rsl.amount());
            }
            return Optional.ofNullable(rsl);
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (accounts) {
            var rsl = false;
            if (accounts.get(fromId) != null
                    && accounts.get(toId) != null
                    && accounts.get(fromId).amount() >= amount) {
                accounts.put(toId, Account.of(toId, accounts.get(toId).amount() + amount));
                accounts.put(fromId, Account.of(fromId, accounts.get(fromId).amount() - amount));
                rsl = true;
            }
            return rsl;
        }
    }
}
