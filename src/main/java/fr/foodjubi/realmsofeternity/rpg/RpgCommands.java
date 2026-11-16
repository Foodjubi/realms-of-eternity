package fr.foodjubi.realmsofeternity.rpg;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

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

                            int current = player.getData(RpgAttachments.MINING_XP);

                            int total = (int) Math.min(
                                    Integer.MAX_VALUE, (long) current + amount);

                            player.setData(RpgAttachments.MINING_XP, total);

                            ctx.getSource().sendSuccess(() -> Component.literal("Minage +" + amount + " XP ; total : " + total),false);

                            return 1;
                        })
                )
        );

    }

}
