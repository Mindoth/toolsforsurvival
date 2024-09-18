package net.mindoth.toolsforsurvival.registries;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.item.BowDrillItem;
import net.mindoth.toolsforsurvival.item.JavelinItem;
import net.mindoth.toolsforsurvival.item.ScytheItem;
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

    public static final RegistryObject<Item> WOODEN_SICKLE = REGISTRY.register("wooden_sickle",
            () -> new ScytheItem(Tiers.WOOD, 2, -3.0f, new Item.Properties().durability(59)));

    public static final RegistryObject<Item> STONE_SICKLE = REGISTRY.register("stone_sickle",
            () -> new ScytheItem(Tiers.STONE, 2, -3.0f, new Item.Properties().durability(131)));

    public static final RegistryObject<Item> IRON_SICKLE = REGISTRY.register("iron_sickle",
            () -> new ScytheItem(Tiers.IRON, 2, -3.0f, new Item.Properties().durability(250)));

    public static final RegistryObject<Item> GOLDEN_SICKLE = REGISTRY.register("golden_sickle",
            () -> new ScytheItem(Tiers.GOLD, 2, -3.0f, new Item.Properties().durability(32)));

    public static final RegistryObject<Item> DIAMOND_SICKLE = REGISTRY.register("diamond_sickle",
            () -> new ScytheItem(Tiers.DIAMOND, 2, -3.0f, new Item.Properties().durability(1562)));

    public static final RegistryObject<Item> NETHERITE_SICKLE = REGISTRY.register("netherite_sickle",
            () -> new ScytheItem(Tiers.NETHERITE, 2, -3.0f, new Item.Properties().durability(2032)));
}
