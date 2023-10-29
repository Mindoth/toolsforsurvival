package net.mindoth.toolsforsurvival;

import net.mindoth.toolsforsurvival.registries.ToolsForSurvivalEntities;
import net.mindoth.toolsforsurvival.registries.ToolsForSurvivalItems;
import net.minecraftforge.api.distmarker.Dist;
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
    }
}
