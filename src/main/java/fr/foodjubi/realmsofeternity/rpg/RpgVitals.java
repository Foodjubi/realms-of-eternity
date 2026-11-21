package fr.foodjubi.realmsofeternity.rpg;

import net.minecraft.server.level.ServerPlayer;

public final class RpgVitals {

    private RpgVitals(){}

    public static int maxMana(){
        double raw = RpgStatCalculator.baseStats().get(RpgStat.MAX_MANA);
        return Math.max(1, (int) raw);
    }

    public static int regenerationPerSecond(){
        double raw = RpgStatCalculator.baseStats().get(RpgStat.MANA_REGEN);
        return Math.max(0, (int) raw);
    }

    public static int mana(ServerPlayer player){
        return RpgVitalsMath.clamp(player.getData(RpgAttachments.MANA), maxMana());
    }

    public static int restoreMana(ServerPlayer player, int amount){
        int before = mana(player);
        int after = RpgVitalsMath.restore(before, amount, maxMana());
        if(after != before){
            player.setData(RpgAttachments.MANA, after);
        }
        return after - before;
    }

    public static boolean spendMana(ServerPlayer player, int cost){
        int current = mana(player);

        if(!RpgVitalsMath.canSpend(current, cost, maxMana())){
            return false;
        }

        player.setData(RpgAttachments.MANA, current - cost);
        return true;
    }

}
