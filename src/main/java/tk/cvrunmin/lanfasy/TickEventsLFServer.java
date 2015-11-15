package tk.cvrunmin.lanfasy;

import java.util.Random;

import tk.cvrunmin.lanfasy.world.WorldProviderAltifect;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class TickEventsLFServer {
	public Random rand1 = new Random();
	public Random rand2 = new Random();
	public Random rand3 = new Random();
	public Random randbool = new Random();
	private Random rand4 = new Random();
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		if (event.phase == Phase.START)
		{
			final WorldServer world = (WorldServer) event.world;
			if (world.provider instanceof WorldProviderAltifect) {
	        	EntityLargeFireball entity = new EntityLargeFireball(world, rand1.nextDouble() * rand4.nextInt(10), 30, rand3.nextDouble() * rand4.nextInt(10), rand1.nextDouble() * (randbool.nextBoolean() ? -1 : 1), rand2.nextDouble(), rand3.nextDouble() * (randbool.nextBoolean() ? -1 : 1));
	        	EntityLargeFireball entity1 = new EntityLargeFireball(world, rand1.nextDouble() * rand4.nextInt(10), 127, rand3.nextDouble() * rand4.nextInt(10), rand1.nextDouble() * (randbool.nextBoolean() ? -1 : 1), rand2.nextDouble() * -1, rand3.nextDouble() * (randbool.nextBoolean() ? -1 : 1));
	        	world.spawnEntityInWorld(entity);
	        	world.spawnEntityInWorld(entity1);
			}
		}
		
	}
}
