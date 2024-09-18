package net.mindoth.toolsforsurvival.item;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
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
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ToolsForSurvival.MOD_ID)
public class ScytheItem extends DiggerItem {

    public ScytheItem(Tier p_43114_, float p_43115_, float p_43116_, Item.Properties p_43117_) {
        super(p_43115_, p_43116_, p_43114_, BlockTags.MINEABLE_WITH_HOE, p_43117_);
    }

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level();
        if ( level.isClientSide ) return;
        if ( player.getAbilities().instabuild ) return;
        if ( !(player.getMainHandItem().getItem() instanceof ScytheItem) ) return;
        BlockState blockState = event.getState();
        Vec3 playerPos = player.getBoundingBox().getCenter();
        BlockPos blockPos = event.getPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        Block block = blockState.getBlock();
        if ( !(block instanceof BushBlock) ) return;
        if ( level instanceof ServerLevel serverLevel ) {
            List<ItemStack> drops = Block.getDrops(blockState, serverLevel, blockPos, blockEntity);
            for ( ItemStack itemStack : drops ) {
                ItemEntity drop = new ItemEntity(level, playerPos.x, playerPos.y, playerPos.z, itemStack);
                drop.setDeltaMovement(0, 0, 0);
                drop.setNoPickUpDelay();
                level.addFreshEntity(drop);
            }
        }
        //level.destroyBlock(blockPos, false);
        level.removeBlock(blockPos, false);
        player.getMainHandItem().hurtAndBreak(1, player, (holder) -> holder.broadcastBreakEvent(EquipmentSlot.MAINHAND));
    }
}
