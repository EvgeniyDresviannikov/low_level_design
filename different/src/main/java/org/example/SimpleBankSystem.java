package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleBankSystem {

    Account[] accounts;

    public SimpleBankSystem(long[] balance) {
        accounts = new Account[balance.length];
        for(int i = 0; i < accounts.length; i++) {
            accounts[i] =  new Account(balance[i]);
        }
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!validateAccount(account1) || !validateAccount(account2) || money < 0) return false;

        Account acc1 = getAccount(account1);
        Account acc2 = getAccount(account2);

        try {
            if (acc1.lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    if (acc2.lock.tryLock(1, TimeUnit.SECONDS)) {
                        try {
                            if (acc1.withdraw(money)) {
                                acc2.deposit(money);
                            } else {
                                return false;
                            }
                        } finally {
                            acc2.lock.unlock();
                        }
                    }
                } finally {
                    acc1.lock.unlock();
                }
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        return true;
    }

    public boolean deposit(int account, long money) {
        if (!validateAccount(account)) return false;

        return getAccount(account).deposit(money);
    }

    public boolean withdraw(int account, long money) {
        if (!validateAccount(account)) return false;

        return getAccount(account).withdraw(money);

    }

    private Account getAccount(int account) {
        return accounts[account-1];
    }

    private boolean validateAccount(int account) {
        if (account > 0 && account <= accounts.length) {
            return true;
        } else {
            return false;
        }
    }


    private static final class Account {
        long balance;
        private final Lock lock = new ReentrantLock(true);

        public Account(long balance) {
            this.balance = balance;
        }

        boolean deposit(long amount) {
            try {
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        balance += amount;
                    } finally {
                        lock.unlock();
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (InterruptedException e) {
                return false;
            }
        }

        boolean withdraw(long amount) {
            try {
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        if (balance < amount) return false;
                        balance -= amount;
                    } finally {
                        lock.unlock();
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (InterruptedException e) {
                return false;
            }
        }
    }
}



