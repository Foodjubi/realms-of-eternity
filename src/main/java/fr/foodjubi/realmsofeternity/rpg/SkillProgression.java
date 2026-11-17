package fr.foodjubi.realmsofeternity.rpg;

public class SkillProgression {

    public static final int MAX_LEVEL = 50;
    private static final int BASE_XP = 100;
    private static final int CURVE_FACTOR = 25;

    public static int xpForNextLevel(int level){

        if(level < 1 || level >= MAX_LEVEL) {
            return 0;
        }

        int index = level - 1;
        return BASE_XP + CURVE_FACTOR * index * index;

    }

    public static int totalXpForLevel(int targetLevel){
        int capped = Math.max(1, Math.min(MAX_LEVEL, targetLevel));
        int total = 0;

        for(int level = 1; level < capped; level++){
            total += xpForNextLevel(level);
        }

        return total;
    }

    public static int levelFromTotalXp(int totalXp){

        int remaining = Math.max(0, totalXp);

        int level = 1;

        while (level < MAX_LEVEL){
            int needed = xpForNextLevel(level);

            if (remaining < needed){
                break;
            }

            remaining -= needed;
            level++;

        }
        return level;
    }

    public static int xpInCurrentLevel(int totalXp){

        int level = levelFromTotalXp(totalXp);

        if(level == MAX_LEVEL){
            return 0;
        }
        return Math.max(0, totalXp) - totalXpForLevel(level);

    }

    public static int xpRemainingToNextLevel(int totalXp){

        int level = levelFromTotalXp(totalXp);

        if(level == MAX_LEVEL){
            return 0;
        }
        return xpForNextLevel(level) - xpInCurrentLevel(totalXp);
    }

    public static int addXpSafely(int currentXp, int amount){

        int safeCurrent = Math.max(0, currentXp);

        if(amount <= 0){
            return safeCurrent;
        }

        long sum = (long) safeCurrent + amount;

        return (int) Math.min((long) Integer.MAX_VALUE, sum);
    }

    private SkillProgression() {}

}
