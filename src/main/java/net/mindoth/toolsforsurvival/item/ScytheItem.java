package net.mindoth.toolsforsurvival.item;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.registries.ToolsForSurvivalItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ToolsForSurvival.MOD_ID)
public class ScytheItem extends SwordItem {
    public ScytheItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }


    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.getLevel();
        if ( level.isClientSide ) return;
        if ( player.getAbilities().instabuild ) return;
        if ( !(player.getMainHandItem().getItem() instanceof ScytheItem) ) return;
        BlockState blockState = event.getState();
        BlockPos playerPos = new BlockPos(player.getBoundingBox().getCenter());
        BlockPos blockPos = event.getPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        Block block = blockState.getBlock();
        if ( !(block instanceof BushBlock) ) return;
        if ( level instanceof ServerLevel serverLevel ) {
            List<ItemStack> drops = Block.getDrops(blockState, serverLevel, blockPos, blockEntity);
            for ( ItemStack itemStack : drops ) {
                ItemEntity drop = new ItemEntity(level, playerPos.getX(), playerPos.getY(), playerPos.getZ(), itemStack);
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
