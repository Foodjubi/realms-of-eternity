package fr.foodjubi.realmsofeternity.rpg;

public class RpgVitalsMath {

    public static int clamp(int value, int maximum){
        int safeMax = Math.max(0, maximum);
        return Math.max(0, Math.min(value, safeMax));
    }

    public static int restore(int current, int amount, int maximum){
        int safeCurrent = clamp(current, maximum);
        if(amount <= 0){
            return safeCurrent;
        }

        long sum = (long) safeCurrent + amount;

        return (int) Math.min((long) Math.max(0, maximum), sum);
    }

    public static boolean canSpend(int current, int cost, int maximum){
        return cost > 0 && clamp(current, maximum) >= cost;
    }
}
