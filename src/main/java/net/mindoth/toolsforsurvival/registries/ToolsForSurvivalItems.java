package net.mindoth.toolsforsurvival.registries;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.item.BowDrillItem;
import net.mindoth.toolsforsurvival.item.JavelinItem;
import net.mindoth.toolsforsurvival.item.ScytheItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ToolsForSurvivalItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ToolsForSurvival.MOD_ID);

    public static final RegistryObject<Item> BOW_DRILL = REGISTRY.register("bowdrill",
            () -> new BowDrillItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> JAVELIN = REGISTRY.register("javelin",
            () -> new JavelinItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> PLANT_FIBER = REGISTRY.register("plant_fiber",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLANT_STRING = REGISTRY.register("plant_string",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WOODEN_SCYTHE = REGISTRY.register("wooden_scythe",
            () -> new ScytheItem(Tiers.WOOD, 4, -3.0f, new Item.Properties().durability(59)));

    public static final RegistryObject<Item> STONE_SCYTHE = REGISTRY.register("stone_scythe",
            () -> new ScytheItem(Tiers.STONE, 4, -3.0f, new Item.Properties().durability(131)));

    public static final RegistryObject<Item> IRON_SCYTHE = REGISTRY.register("iron_scythe",
            () -> new ScytheItem(Tiers.IRON, 4, -3.0f, new Item.Properties().durability(250)));

    public static final RegistryObject<Item> GOLDEN_SCYTHE = REGISTRY.register("golden_scythe",
            () -> new ScytheItem(Tiers.GOLD, 4, -3.0f, new Item.Properties().durability(32)));

    public static final RegistryObject<Item> DIAMOND_SCYTHE = REGISTRY.register("diamond_scythe",
            () -> new ScytheItem(Tiers.DIAMOND, 4, -3.0f, new Item.Properties().durability(1562)));

    public static final RegistryObject<Item> NETHERITE_SCYTHE = REGISTRY.register("netherite_scythe",
            () -> new ScytheItem(Tiers.NETHERITE, 4, -3.0f, new Item.Properties().durability(2032)));
}
