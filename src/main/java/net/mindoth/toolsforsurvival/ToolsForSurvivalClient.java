package net.mindoth.toolsforsurvival;

import net.mindoth.toolsforsurvival.client.ThrownJavelinModel;
import net.mindoth.toolsforsurvival.client.ThrownJavelinRenderer;
import net.mindoth.toolsforsurvival.client.ToolsForSurvivalLayers;
import net.mindoth.toolsforsurvival.client.ToolsForSurvivalProperties;
import net.mindoth.toolsforsurvival.registries.ModEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ToolsForSurvivalClient {

    public static void registerHandlers(IEventBus modBus, ModContainer modContainer) {
        modBus.addListener(ToolsForSurvivalClient::clientSetup);
        modBus.addListener(ToolsForSurvivalClient::registerEntityRenderers);
        modBus.addListener(ToolsForSurvivalClient::onRegisterLayerDefinitions);
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
        ToolsForSurvivalProperties.addCustomItemProperties();
    }

    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ToolsForSurvivalLayers.THROWN_JAVELIN_LAYER, ThrownJavelinModel::createBodyLayer);
    }

    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWN_JAVELIN.get(), ThrownJavelinRenderer::new);
    }
}
