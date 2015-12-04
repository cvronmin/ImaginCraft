package tk.cvrunmin.lanfasy.event;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tk.cvrunmin.lanfasy.entity.cy.EntityMess;

public class EventMessBoss {
	@SubscribeEvent
	public void onEntityBeenAttacked(LivingHurtEvent event){
		if (event.entityLiving instanceof EntityMess) {
			if (((EntityMess)event.entityLiving).isInvulnerable() && event.source != DamageSource.generic && event.source != DamageSource.outOfWorld && event.isCancelable()) {
				event.setCanceled(true);
			}
		}
	}
}
