package fr.foodjubi.realmsofeternity.rpg;

public enum RpgStatGroup {
    SURVIVAL("Survival"),
    COMBAT("Combat"),
    MAGIC("Magic"),
    MOBILITY("Mobilité"),
    GATHERING("Recolte");

    private final String displayName;

    RpgStatGroup(String displayName){
        this.displayName = displayName;
    }

    public String displayName(){
        return displayName;
    }

}
