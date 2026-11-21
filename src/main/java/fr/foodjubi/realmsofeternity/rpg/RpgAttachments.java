package fr.foodjubi.realmsofeternity.rpg;

import net.neoforged.neoforge.attachment.AttachmentType;

import com.mojang.serialization.Codec;
import java.util.function.Supplier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import fr.foodjubi.realmsofeternity.RealmsOfEternity;

public final class RpgAttachments {

    private static final DeferredRegister<AttachmentType<?>> TYPES =
            DeferredRegister.create(
                    NeoForgeRegistries.ATTACHMENT_TYPES,
                    RealmsOfEternity.MODID
            );

    public static final Supplier<AttachmentType<Integer>> MINING_XP =
            TYPES.register("mining_xp", () -> AttachmentType
                    .builder(() -> 0)
                    .serialize(Codec.INT.fieldOf("value"))
                    .copyOnDeath()
                    .build());

    public static final Supplier<AttachmentType<Integer>> COMBAT_XP =
            TYPES.register("combat_xp", () -> AttachmentType
                    .builder(() -> 0)
                    .serialize(Codec.INT.fieldOf("value"))
                    .copyOnDeath()
                    .build());

    public static final Supplier<AttachmentType<Integer>> MANA =
            TYPES.register("mana", () -> AttachmentType
                    .builder(() -> 50)
                    .serialize(Codec.INT.fieldOf("value"))
                    .copyOnDeath()
                    .build()
            );

    public static void register(IEventBus modBus){
        TYPES.register(modBus);
    }

    private RpgAttachments() {}
}
