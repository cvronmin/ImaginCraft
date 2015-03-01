package tk.cvrunmin.lanfasy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BucketHandlerLF {
	public static BucketHandlerLF INSTANCE = new BucketHandlerLF();
	public Map<Block, Item> buckets = new HashMap();

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event) {
		ItemStack result = fillCustomBucket(event.world, event.target);

		if (result == null) {
			return;
		}

		event.result = result;
		event.setResult(Event.Result.ALLOW);
	}

	private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
        BlockPos blockpos = pos.getBlockPos();
		Block block = world.getBlockState(pos.getBlockPos()).getBlock();
        IBlockState iblockstate = world.getBlockState(blockpos);
		Item bucket = (Item) this.buckets.get(block);

		if ((bucket != null)
				&& (block.getMetaFromState(iblockstate) == 0)) {
			world.setBlockToAir(blockpos);
			return new ItemStack(bucket);
		}
		return null;
	}
}
