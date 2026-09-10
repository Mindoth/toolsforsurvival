package net.mindoth.toolsforsurvival.registries;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.item.BowDrillItem;
import net.mindoth.toolsforsurvival.item.JavelinItem;
import net.mindoth.toolsforsurvival.item.PlantFiberItem;
import net.mindoth.toolsforsurvival.item.SickleItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = ToolsForSurvival.MOD_ID)
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ToolsForSurvival.MOD_ID);

    @SubscribeEvent
    public static void addToTab(BuildCreativeModeTabContentsEvent event) {
        if ( event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES ) {
            event.accept(ModItems.BOW_DRILL);
            event.accept(ModItems.WOODEN_SICKLE);
            event.accept(ModItems.STONE_SICKLE);
            event.accept(ModItems.IRON_SICKLE);
            event.accept(ModItems.GOLDEN_SICKLE);
            event.accept(ModItems.DIAMOND_SICKLE);
            event.accept(ModItems.NETHERITE_SICKLE);
        }
        if ( event.getTabKey() == CreativeModeTabs.COMBAT ) {
            event.accept(ModItems.JAVELIN);
        }
        if ( event.getTabKey() == CreativeModeTabs.INGREDIENTS ) {
            event.accept(ModItems.PLANT_FIBER);
            //event.accept(ModItems.PLANT_STRING);
        }
    }

    public static final DeferredItem<Item> BOW_DRILL = ITEMS.register("bowdrill",
            () -> new BowDrillItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> JAVELIN = ITEMS.register("javelin",
            () -> new JavelinItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> PLANT_FIBER = ITEMS.register("plant_fiber",
            () -> new PlantFiberItem(new Item.Properties()));

    /*
    public static final DeferredItem<Item> PLANT_STRING = ITEMS.register("plant_string",
            () -> new Item(new Item.Properties()));
    */

    public static final DeferredItem<Item> WOODEN_SICKLE = ITEMS.register("wooden_sickle",
            () -> new SickleItem(Tiers.WOOD, 2, -3.0f, new Item.Properties().durability(59)));

    public static final DeferredItem<Item> STONE_SICKLE = ITEMS.register("stone_sickle",
            () -> new SickleItem(Tiers.STONE, 2, -3.0f, new Item.Properties().durability(131)));

    public static final DeferredItem<Item> IRON_SICKLE = ITEMS.register("iron_sickle",
            () -> new SickleItem(Tiers.IRON, 2, -3.0f, new Item.Properties().durability(250)));

    public static final DeferredItem<Item> GOLDEN_SICKLE = ITEMS.register("golden_sickle",
            () -> new SickleItem(Tiers.GOLD, 2, -3.0f, new Item.Properties().durability(32)));

    public static final DeferredItem<Item> DIAMOND_SICKLE = ITEMS.register("diamond_sickle",
            () -> new SickleItem(Tiers.DIAMOND, 2, -3.0f, new Item.Properties().durability(1562)));

    public static final DeferredItem<Item> NETHERITE_SICKLE = ITEMS.register("netherite_sickle",
            () -> new SickleItem(Tiers.NETHERITE, 2, -3.0f, new Item.Properties().durability(2032)));
}
