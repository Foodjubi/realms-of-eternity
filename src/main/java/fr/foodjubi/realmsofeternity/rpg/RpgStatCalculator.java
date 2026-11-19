package fr.foodjubi.realmsofeternity.rpg;

import java.util.EnumMap;
import java.util.Map;
import java.util.StringJoiner;

public final class RpgStatCalculator {

    private RpgStatCalculator(){}

    public static EnumMap<RpgStat, Double> baseStat(){

        EnumMap<RpgStat, Double> stats = new EnumMap<>(RpgStat.class);

        for(RpgStat stat : RpgStat.values()){
            double base = switch (stat){
                case MAX_HEALTH -> 20.0;
                case MAX_MANA -> 50.0;
                default -> 0.0;
            };
            stats.put(stat, base);
        }

        return stats;
    }

    public static String describeGroup(Map<RpgStat, Double> stats, RpgStatGroup group){

        StringJoiner parts = new StringJoiner(" | ");

        for (RpgStat stat : RpgStat.values()){
            if (stat.group() == group){
                double value = stats.getOrDefault(stat, 0.0);
                parts.add(stat.label() + " : " + value);
            }
        }
        return parts.toString();
    }

}
