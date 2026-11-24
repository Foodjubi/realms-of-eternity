package fr.foodjubi.realmsofeternity;

import fr.foodjubi.realmsofeternity.rpg.RpgAttachments;
import fr.foodjubi.realmsofeternity.rpg.RpgCommands;
import fr.foodjubi.realmsofeternity.rpg.RpgVitalCommands;
import fr.foodjubi.realmsofeternity.rpg.RpgVitalEvents;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(RealmsOfEternity.MODID)
public class RealmsOfEternity {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "realmsofeternity";

    public RealmsOfEternity(IEventBus modEventBus, ModContainer modContainer) {
        RpgAttachments.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(RpgCommands::register);
        NeoForge.EVENT_BUS.addListener(RpgVitalCommands::register);
        NeoForge.EVENT_BUS.addListener(RpgVitalEvents::onPlayerTick);
    }

}
