package net.mindoth.toolsforsurvival.loot;

import com.mojang.serialization.Codec;
import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ToolsForSurvivalLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ToolsForSurvival.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> PLANT_FIBER_FROM_GRASS =
            LOOT_MODIFIER_SERIALIZERS.register("plant_fiber_from_grass", PlantFiberAdditionModifier.CODEC);

    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}
