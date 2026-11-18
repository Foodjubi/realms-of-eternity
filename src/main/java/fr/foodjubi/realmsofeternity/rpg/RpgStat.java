package fr.foodjubi.realmsofeternity.rpg;

public enum RpgStat {
    MAX_HEALTH("PV max", RpgStatGroup.SURVIVAL),
    DEFENSE("Défense", RpgStatGroup.SURVIVAL),
    HEALTH_REGEN("Régénération PV", RpgStatGroup.SURVIVAL),
    RESISTANCE("Résistance", RpgStatGroup.SURVIVAL),
    LIFE_STEAL("Vol de vie", RpgStatGroup.SURVIVAL),
    STRENGTH("Force", RpgStatGroup.COMBAT),
    CRIT_CHANCE("Chance critique", RpgStatGroup.COMBAT),
    CRIT_DAMAGE("Dégâts critiques", RpgStatGroup.COMBAT),
    ACCURACY("Précision", RpgStatGroup.COMBAT),
    ARMOR_PENETRATION("Pénétration armure", RpgStatGroup.COMBAT),
    ATTACK_SPEED("Vitesse d'attaque", RpgStatGroup.COMBAT),
    INTELLIGENCE("Intelligence", RpgStatGroup.MAGIC),
    MAX_MANA("Mana max", RpgStatGroup.MAGIC),
    MANA_REGEN("Régénération mana", RpgStatGroup.MAGIC),
    ELEMENTAL_POWER("Puissance élémentaire", RpgStatGroup.MAGIC),
    MAGIC_RESISTANCE("Résistance magique", RpgStatGroup.MAGIC),
    MOVEMENT_SPEED("Vitesse de déplacement", RpgStatGroup.MOBILITY),
    JUMP("Saut", RpgStatGroup.MOBILITY),
    DODGE("Esquive", RpgStatGroup.MOBILITY),
    LUCK("Chance", RpgStatGroup.GATHERING),
    MAGIC_FIND("Magic find", RpgStatGroup.GATHERING),
    MINING_FORTUNE("Fortune Minage", RpgStatGroup.GATHERING),
    FARMING_FORTUNE("Fortune Agriculture", RpgStatGroup.GATHERING),
    FORAGING_FORTUNE("Fortune Récolte forestière", RpgStatGroup.GATHERING);

    private final String label;
    private final RpgStatGroup group;

    RpgStat(String label, RpgStatGroup group){
        this.label = label;
        this.group = group;
    }

    public String label(){
        return label;
    }

    public RpgStatGroup group(){
        return group;
    }

}
