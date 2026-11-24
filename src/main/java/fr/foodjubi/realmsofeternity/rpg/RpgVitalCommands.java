package fr.foodjubi.realmsofeternity.rpg;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/** Commandes de test (pas encore des capacités RPG définitives). */
public final class RpgVitalCommands {

    private RpgVitalCommands() {}

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("roe_vitals")
                        .executes(ctx -> {
                            ServerPlayer player =
                                    ctx.getSource().getPlayerOrException();
                            String line = "PV " + player.getHealth()
                                    + "/" + player.getMaxHealth()
                                    + " | Mana " + RpgVitals.mana(player)
                                    + "/" + RpgVitals.maxMana()
                                    + " (regen +"
                                    + RpgVitals.regenerationPerSecond()
                                    + "/s)";
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal(line), false);
                            return 1;
                        })
        );

        event.getDispatcher().register(
                Commands.literal("roe_spendmana")
                        .then(Commands.argument(
                                        "amount", IntegerArgumentType.integer(1, 1000))
                                .executes(ctx -> {
                                    ServerPlayer player =
                                            ctx.getSource().getPlayerOrException();
                                    int amount = IntegerArgumentType.getInteger(
                                            ctx, "amount");
                                    if (!RpgVitals.spendMana(player, amount)) {
                                        ctx.getSource().sendFailure(Component.literal(
                                                "Mana insuffisant !"));
                                        return 0;
                                    }
                                    int remaining = RpgVitals.mana(player);
                                    ctx.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "-" + amount + " mana | reste "
                                                            + remaining), false);
                                    return 1;
                                })
                        )
        );

        event.getDispatcher().register(
                Commands.literal("roe_restoremana")
                        .then(Commands.argument(
                                        "amount", IntegerArgumentType.integer(1, 1000))
                                .executes(ctx -> {
                                    ServerPlayer player =
                                            ctx.getSource().getPlayerOrException();
                                    int amount = IntegerArgumentType.getInteger(
                                            ctx, "amount");
                                    int restored =
                                            RpgVitals.restoreMana(player, amount);
                                    int remaining = RpgVitals.mana(player);
                                    ctx.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "+" + restored + " mana | total "
                                                            + remaining), false);
                                    return 1;
                                })
                        )
        );

        event.getDispatcher().register(
                Commands.literal("roe_castheal")
                        .executes(ctx -> {
                            ServerPlayer player =
                                    ctx.getSource().getPlayerOrException();
                            if (player.getHealth() >= player.getMaxHealth()) {
                                ctx.getSource().sendFailure(Component.literal(
                                        "Tes PV sont deja au maximum."));
                                return 0;
                            }
                            if (!RpgVitals.spendMana(player, 10)) {
                                ctx.getSource().sendFailure(Component.literal(
                                        "Il faut 10 mana pour lancer le soin."));
                                return 0;
                            }
                            float before = player.getHealth();
                            player.heal(4.0F);
                            float gained = player.getHealth() - before;
                            int remaining = RpgVitals.mana(player);
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Soin : +" + gained
                                                    + " PV | mana restant "
                                                    + remaining), false);
                            return 1;
                        })
        );
    }
}
