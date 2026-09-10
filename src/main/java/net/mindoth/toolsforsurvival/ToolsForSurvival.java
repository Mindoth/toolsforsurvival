package net.mindoth.toolsforsurvival;

import net.mindoth.toolsforsurvival.registries.ModEntities;
import net.mindoth.toolsforsurvival.registries.ModItems;
import net.mindoth.toolsforsurvival.registries.ModLootModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(ToolsForSurvival.MOD_ID)
public class ToolsForSurvival {
    public static final String MOD_ID = "toolsforsurvival";

    public ToolsForSurvival(IEventBus modBus, ModContainer modContainer, Dist dist) {
        if ( dist.isClient() ) ToolsForSurvivalClient.registerHandlers(modBus, modContainer);
        addRegistries(modBus);
    }

    private void addRegistries(final IEventBus modBus) {
        ModItems.ITEMS.register(modBus);
        ModEntities.ENTITIES.register(modBus);
        ModLootModifiers.register(modBus);
    }
}
