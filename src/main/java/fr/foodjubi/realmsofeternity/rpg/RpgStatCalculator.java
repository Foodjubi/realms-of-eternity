package fr.foodjubi.realmsofeternity.rpg;

import java.util.EnumMap;
import java.util.Map;
import java.util.StringJoiner;

public final class RpgStatCalculator {

    private RpgStatCalculator(){}

    public static EnumMap<RpgStat, Double> baseStats(){

        EnumMap<RpgStat, Double> stats = new EnumMap<>(RpgStat.class);

        for(RpgStat stat : RpgStat.values()){
            double base = switch (stat){
                case MAX_HEALTH -> 20.0;
                case MAX_MANA -> 50.0;
                case MANA_REGEN -> 1.0;
                default -> 0.0;
            };
            stats.put(stat, base);
        }

        return stats;
    }

    public static EnumMap<RpgStat, Double> previewBonus(Map<RpgStat, Double> source, RpgStat target, double bonus){

        EnumMap<RpgStat, Double> copy = new EnumMap<>(RpgStat.class);

        for (RpgStat stat : RpgStat.values()){
            copy.put(stat, source.getOrDefault(stat, 0.0));
        }

        copy.put(target, copy.get(target) + bonus);

        return copy;

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
