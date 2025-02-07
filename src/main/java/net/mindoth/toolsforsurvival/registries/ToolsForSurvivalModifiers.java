package net.mindoth.toolsforsurvival.registries;

import com.mojang.serialization.Codec;
import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.loot.AddItemModifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ToolsForSurvivalModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ToolsForSurvival.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", AddItemModifier.CODEC);
}
