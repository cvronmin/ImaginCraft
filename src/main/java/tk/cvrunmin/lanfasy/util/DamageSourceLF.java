package tk.cvrunmin.lanfasy.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import tk.cvrunmin.lanfasy.entity.projectile.EntityFireArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityIrrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntitySafeIrrow;
import tk.cvrunmin.lanfasy.world.Ionplozation;

public class DamageSourceLF extends DamageSource{
	public static final DamageSourceLF enterDamagedArea = (DamageSourceLF) new DamageSourceLF("enterDamagedArea").setDamageBypassesArmor();
	public DamageSourceLF(String damageType) {
		super(damageType);
	}
    public static DamageSource causeFireArrowDamage(EntityFireArrow arrow, Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("fire_arrow", (Entity)arrow, p_76353_1_)).setProjectile();
    }
    public static DamageSource causeIrrowDamage(EntitySafeIrrow arrow, Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("irrow", (Entity)arrow, p_76353_1_)).setProjectile();
    }
	public static DamageSource setIonplozationSource(Ionplozation ionplozation) {
        return ionplozation != null && ionplozation.getExplosivePlacedBy() != null ? (new EntityDamageSource("ionplozation.player", ionplozation.getExplosivePlacedBy())).setDifficultyScaled().setExplosion() : (new DamageSource("ionplozation")).setDifficultyScaled().setExplosion();
	}
}
