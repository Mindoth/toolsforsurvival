package net.mindoth.toolsforsurvival;

import net.mindoth.toolsforsurvival.loot.ToolsForSurvivalLootModifiers;
import net.mindoth.toolsforsurvival.registries.ToolsForSurvivalEntities;
import net.mindoth.toolsforsurvival.registries.ToolsForSurvivalItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(ToolsForSurvival.MOD_ID)
public class ToolsForSurvival {
    public static final String MOD_ID = "toolsforsurvival";

    public ToolsForSurvival() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ToolsForSurvivalClient.registerHandlers();
        }
        addRegistries(modEventBus);
    }

    private void addRegistries(final IEventBus modEventBus) {
        ToolsForSurvivalItems.REGISTRY.register(modEventBus);
        ToolsForSurvivalEntities.ENTITIES.register(modEventBus);
        ToolsForSurvivalLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if ( event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES ) {
            event.accept(ToolsForSurvivalItems.BOW_DRILL);
            event.accept(ToolsForSurvivalItems.WOODEN_SCYTHE);
            event.accept(ToolsForSurvivalItems.STONE_SCYTHE);
            event.accept(ToolsForSurvivalItems.IRON_SCYTHE);
            event.accept(ToolsForSurvivalItems.GOLDEN_SCYTHE);
            event.accept(ToolsForSurvivalItems.DIAMOND_SCYTHE);
            event.accept(ToolsForSurvivalItems.NETHERITE_SCYTHE);
        }
        if ( event.getTabKey() == CreativeModeTabs.COMBAT ) {
            event.accept(ToolsForSurvivalItems.JAVELIN);
        }
        if ( event.getTabKey() == CreativeModeTabs.INGREDIENTS ) {
            event.accept(ToolsForSurvivalItems.PLANT_FIBER);
            event.accept(ToolsForSurvivalItems.PLANT_STRING);
        }
    }
}
