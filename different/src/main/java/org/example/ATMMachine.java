package org.example;

public class ATMMachine {
    int[] banknotes;
    private static final int[] NOMINALS = new int[] {20, 50, 100, 200, 500};

    public ATMMachine() {
        banknotes = new int[5];
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            banknotes[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] res = new int[5];

        for (int i = banknotes.length-1; i > -1; i--) {
            int cnt = Math.min(banknotes[i], amount / NOMINALS[i]);
            res[i] = cnt;
            amount -= cnt*NOMINALS[i];
        }

        if (amount != 0) {
            return new int[]{-1};
        }

        for (int i = 0; i < banknotes.length; i++) {
            banknotes[i] -= res[i];
        }

        return res;
    }
}


