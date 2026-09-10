package net.mindoth.toolsforsurvival.item;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.List;

@EventBusSubscriber(modid = ToolsForSurvival.MOD_ID)
public class SickleItem extends DiggerItem {

    public SickleItem(Tier tier, float damage, float speed, Item.Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_HOE, properties.attributes(createAttributes(tier, damage, speed)));
    }

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level();
        if ( level.isClientSide ) return;
        if ( player.getAbilities().instabuild ) return;
        if ( !(player.getMainHandItem().getItem() instanceof SickleItem) ) return;
        BlockState blockState = event.getState();
        Vec3 playerPos = player.getBoundingBox().getCenter();
        BlockPos blockPos = event.getPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        Block block = blockState.getBlock();
        if ( !(block instanceof BushBlock) ) return;
        if ( level instanceof ServerLevel serverLevel ) {
            List<ItemStack> drops = Block.getDrops(blockState, serverLevel, blockPos, blockEntity, player, player.getMainHandItem());
            //if ( new Random().nextFloat() <= 0.1F ) drops.add(new ItemStack(ToolsForSurvivalItems.PLANT_FIBER.get()));
            for ( ItemStack itemStack : drops ) {
                ItemEntity drop = new ItemEntity(level, playerPos.x, playerPos.y, playerPos.z, itemStack);
                drop.setDeltaMovement(0, 0, 0);
                drop.setNoPickUpDelay();
                level.addFreshEntity(drop);
            }
        }
        //level.destroyBlock(blockPos, false);
        level.removeBlock(blockPos, false);

        if ( level instanceof ServerLevel serverLevel ) {
            ItemStack stack = player.getMainHandItem();
            stack.hurtAndBreak(1, serverLevel, player,
                    (holder) -> player.onEquippedItemBroken(stack.getItem(), player.getEquipmentSlotForItem(stack)));
        }
    }
}
