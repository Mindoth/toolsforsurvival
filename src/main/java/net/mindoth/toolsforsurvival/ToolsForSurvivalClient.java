package net.mindoth.toolsforsurvival;

import net.mindoth.toolsforsurvival.client.ThrownJavelinModel;
import net.mindoth.toolsforsurvival.client.ThrownJavelinRenderer;
import net.mindoth.toolsforsurvival.client.ToolsForSurvivalLayers;
import net.mindoth.toolsforsurvival.registries.ToolsForSurvivalEntities;
import net.mindoth.toolsforsurvival.util.ModItemProperties;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ToolsForSurvivalClient {

    public static void registerHandlers() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(ToolsForSurvivalClient::clientSetup);
        modBus.addListener(ToolsForSurvivalClient::registerEntityRenderers);
        modBus.addListener(ToolsForSurvivalClient::onRegisterLayerDefinitions);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();
    }

    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ToolsForSurvivalLayers.THROWN_JAVELIN_LAYER, ThrownJavelinModel::createBodyLayer);
    }

    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ToolsForSurvivalEntities.THROWN_JAVELIN.get(), ThrownJavelinRenderer::new);
    }
}
