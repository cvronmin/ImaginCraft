package tk.cvrunmin.lanfasy.entity.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGreenstal extends EntityAFCrystal{
    /** Used to create the rotation animation when rendering the crystal. */
    public int innerRotation;
    public int health;
    public EntityAFCrystal healee;
    
    public EntityGreenstal(World worldIn)
    {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(2.0F, 2.0F);
        this.health = 5;
        this.innerRotation = this.rand.nextInt(100000);
    }
    @SideOnly(Side.CLIENT)
    public EntityGreenstal(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.setPosition(x, y, z);
    }
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(8, Integer.valueOf(this.health));
    }
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.innerRotation;
        this.dataWatcher.updateObject(8, Integer.valueOf(this.health));
        if(!this.isDead){
        	checkCrystal();
        }
    }
    private void checkCrystal(){
        if(healee != null){
            if (this.healee.isDead)
            {
                this.healee = null;
            }
            else if (this.ticksExisted % 10 == 0)
            {
            	if(healee.getHealth() < healee.getMaxHealth()){
                healee.setHealth(healee.getHealth() + 1);
            	}
            }
        }
        if (this.rand.nextInt(10) == 0)
        {
        	EntityAFCrystal entity = null;
        	List list = this.worldObj.getEntitiesWithinAABB(EntityAFCrystal.class, this.getEntityBoundingBox().expand(30D, 30D, 30D));
            Iterator iterator = list.iterator();
            double d0 = Double.MAX_VALUE;
            while (iterator.hasNext())
            {
                EntityAFCrystal entity1 = (EntityAFCrystal)iterator.next();
                double d1 = entity1.getDistanceSqToEntity(this);
                if (d1 < d0 && d1 > 0)
                {
                    d0 = d1;
                    entity = entity1;
                }
            }
            this.healee = entity;
        }
    }
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {

	}
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {

	}
    public boolean canBeCollidedWith()
    {
        return true;
    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
                this.health -= 1;

                if (this.health <= 0)
                {
                    this.setDead();

                    if (!this.worldObj.isRemote)
                    {
                        this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 6.0F, true);
                    }
                }
            }

            return true;
        }
    }
}
