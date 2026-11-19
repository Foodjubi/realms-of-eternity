package fr.foodjubi.realmsofeternity.rpg;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.EnumMap;

public final class RpgCommands {

    private RpgCommands() {}

    public static void register(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal("roe_profile")
                .executes(ctx -> {
                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                    int mining = player.getData(RpgAttachments.MINING_XP);
                    int combat = player.getData(RpgAttachments.COMBAT_XP);

                    ctx.getSource().sendSuccess(() -> Component.literal(
                            "Minage : " + mining +" XP | Combat " + combat + "XP"),
                            false
                    );
                    return 1;
                })
        );

        event.getDispatcher().register(Commands.literal("roe_testxp")
                .then(Commands.argument("amount", IntegerArgumentType.integer(1, 1000))
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            int amount = IntegerArgumentType.getInteger(ctx, "amount");
                            int before = player.getData(RpgAttachments.MINING_XP);
                            int oldLevel = SkillProgression.levelFromTotalXp(before);
                            int after = SkillProgression.addXpSafely(before, amount);
                            player.setData(RpgAttachments.MINING_XP, after);
                            int gained = after - Math.max(0, before);
                            int newLevel = SkillProgression.levelFromTotalXp(after);
                            String bonus = newLevel > oldLevel ? " | Niveau " + oldLevel + " -> " + newLevel : "";
                            String cap = gained < amount ? " (plafond atteint)" : "";
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal("Minage +" + gained
                                            + " XP | total " + after + bonus + cap), false);
                            return 1;
                        })
                )
        )
        ;event.getDispatcher().register(Commands.literal("roe_testcombatxp")
                .then(Commands.argument("amount", IntegerArgumentType.integer(1, 1000))
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            int amount = IntegerArgumentType.getInteger(ctx, "amount");
                            int before = player.getData(RpgAttachments.COMBAT_XP);
                            int oldLevel = SkillProgression.levelFromTotalXp(before);
                            int after = SkillProgression.addXpSafely(before, amount);
                            player.setData(RpgAttachments.COMBAT_XP, after);
                            int gained = after - Math.max(0, before);
                            int newLevel = SkillProgression.levelFromTotalXp(after);
                            String bonus = newLevel > oldLevel ? " | Niveau " + oldLevel + " -> " + newLevel : "";
                            String cap = gained < amount ? " (plafond atteint)" : "";
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal("Combat +" + gained
                                            + " XP | total " + after + bonus + cap), false);
                            return 1;
                        })
                )
        );
        event.getDispatcher().register(
                Commands.literal("roe_stats")
                        .executes(ctx -> {
                            ctx.getSource().getPlayerOrException();
                            EnumMap<RpgStat, Double> stats = RpgStatCalculator.baseStats();
                            ctx.getSource().sendSuccess( () -> Component.literal("=== Statistiques RPG (prototype) ==="), false);
                            for (RpgStatGroup group : RpgStatGroup.values()){
                                String line = group.displayName() + " : " + RpgStatCalculator.describeGroup(stats, group);
                                ctx.getSource().sendSuccess(() -> Component.literal(line), false);
                            }
                            return 1;
                        })
        );
        event.getDispatcher().register(
                Commands.literal("roe_statdemo")
                        .executes(ctx -> {
                            ctx.getSource().getPlayerOrException();
                            EnumMap<RpgStat, Double> base =
                                    RpgStatCalculator.baseStats();
                            EnumMap<RpgStat, Double> preview =
                                    RpgStatCalculator.previewBonus(
                                            base, RpgStat.STRENGTH, 5.0);
                            String message = "Force de base : "
                                    + base.get(RpgStat.STRENGTH)
                                    + " | Aperçu +5 : "
                                    + preview.get(RpgStat.STRENGTH)
                                    + " | Base inchangée.";
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal(message), false);
                            return 1;
                        })
        );


    }

    private static String describeSkill(String label, int totalXp){

        int level = SkillProgression.levelFromTotalXp(totalXp);

        if(level == SkillProgression.MAX_LEVEL){
            return label + " nev. 50 (MAX), XP total " + totalXp;
        }

        int progress = SkillProgression.xpInCurrentLevel(totalXp);
        int needed = SkillProgression.xpForNextLevel(level);

        return label + " niv. " + level + " (" + progress + "/" + needed + " XP), total : " + totalXp;
    }

}
