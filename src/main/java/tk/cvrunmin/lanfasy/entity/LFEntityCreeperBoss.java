package tk.cvrunmin.lanfasy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.entity.ai.EntityAICreeperBossSwell;

public class LFEntityCreeperBoss extends EntityMob implements IBossDisplayData{
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 3;
    private int field_175494_bm = 0;
    private int spawnSlaveTime;
	public LFEntityCreeperBoss(World worldIn) {
		super(worldIn);
		this.setSize(4f, 4f);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAICreeperBossSwell(this));
        this.tasks.addTask(2, this.aiAvoidExplodingCreepers);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityOcelot.class, 24.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityOcelot.class, false));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	}
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000D);
    }
    public boolean isAIEnabled()
    {
        return this.field_175494_bm < 1 && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot");
    }
    public int getMaxFallHeight()
    {
        return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
    }
    public void fall(float distance, float damageMultiplier)
    {
        super.fall(distance, damageMultiplier);
        this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + distance * 1.5F);

        if (this.timeSinceIgnited > this.fuseTime - 5)
        {
            this.timeSinceIgnited = this.fuseTime - 5;
        }
    }
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte) - 1));
        this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
    }
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);

        par1NBTTagCompound.setShort("Fuse", (short)this.fuseTime);
        par1NBTTagCompound.setByte("ExplosionRadius", (byte)this.explosionRadius);
        par1NBTTagCompound.setBoolean("ignited", this.func_146078_ca());
    }
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);

        if (tagCompound.hasKey("Fuse", 99))
        {
            this.fuseTime = tagCompound.getShort("Fuse");
        }

        if (tagCompound.hasKey("ExplosionRadius", 99))
        {
            this.explosionRadius = tagCompound.getByte("ExplosionRadius");
        }

        if (tagCompound.getBoolean("ignited"))
        {
            this.func_146079_cb();
        }
    }
    public void onDeath(DamageSource cause)
    {	
        super.onDeath(cause);

        if (cause.getEntity() instanceof EntitySkeleton || cause.getEntity() instanceof LFEntitySkeleton)
        {
            int i = Item.getIdFromItem(Items.record_13);
            int j = Item.getIdFromItem(Items.record_wait);
            int k = i + this.rand.nextInt(j - i + 1);
            this.dropItem(Item.getItemById(k), 1);
            int j1 = 2 + this.rand.nextInt(3);
            for (int k1 = 0; k1 < j1; ++k1)
            {
                float f = ((float)(k1 % 2) - 0.5F) * (float)4 / 4.0F;
                float f1 = ((float)(k1 / 2) - 0.5F) * (float)4 / 4.0F;
                LFEntityCreeper entity = new LFEntityCreeper(this.worldObj);

                if (this.hasCustomName())
                {
                    entity.setCustomNameTag(this.getCustomNameTag());
                }

                if (this.isNoDespawnRequired())
                {
                    entity.enablePersistence();
                }
                entity.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entity);
            }
        }
    }
    public void onUpdate()
    {
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.func_146078_ca())
            {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound("lanfasy:lfcreeper.primed", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
            ++spawnSlaveTime;
            if(spawnSlaveTime >= 2000){
            	if(!this.worldObj.isRemote){
                int j1 = 10 + this.rand.nextInt(10);
                for (int k1 = 0; k1 < j1; ++k1)
                {
                    float f = ((float)(k1 % 2) - 0.5F) * (float)4 / 4.0F;
                    float f1 = ((float)(k1 / 2) - 0.5F) * (float)4 / 4.0F;
                    LFEntityCreeper entity = new LFEntityCreeper(this.worldObj);

                    if (this.hasCustomName())
                    {
                        entity.setCustomNameTag(this.getCustomNameTag());
                    }

                    if (this.isNoDespawnRequired())
                    {
                        entity.enablePersistence();
                    }
                    entity.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                    this.worldObj.spawnEntityInWorld(entity);
                }
            	}
                spawnSlaveTime = 0;
            }
        }
        super.onUpdate();
    }
    protected String getHurtSound()
    {
        return "lanfasy:lfmob.creeper.say";
    }
    protected String getDeathSound()
    {
        return "lanfasy:lfmob.creeper.death";
    }
    public int getCreeperState()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }
    public void setCreeperState(int par1)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)par1));
    }
    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        super.onStruckByLightning(lightningBolt);
        LFEntityCreeper entity = new LFEntityCreeper(worldObj);
        entity.setPowered((byte)1);
        entity.setLocationAndAngles(this.posX, this.posY + 0.5D, this.posZ, this.rand.nextFloat() * 360.0F, 0.0F);
        this.worldObj.spawnEntityInWorld(entity);
    }
    protected boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.flint_and_steel)
        {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.ignite", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            par1EntityPlayer.swingItem();

            if (!this.worldObj.isRemote)
            {
                this.func_146079_cb();
                itemstack.damageItem(1, par1EntityPlayer);
                return true;
            }
        }

        return super.interact(par1EntityPlayer);
    }
    private void explode()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
            float f = 2.0F;
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius * f + 0.5f, flag);
            this.worldObj.createExplosion(this, this.posX + 10, this.posY + 10, this.posZ + 10, (float)this.explosionRadius * f, flag);
            this.worldObj.createExplosion(this, this.posX - 10, this.posY + 10, this.posZ + 10, (float)this.explosionRadius * f, flag);
            this.worldObj.createExplosion(this, this.posX - 10, this.posY - 10, this.posZ + 10, (float)this.explosionRadius * f, flag);
            this.worldObj.createExplosion(this, this.posX - 10, this.posY - 10, this.posZ - 10, (float)this.explosionRadius * f, flag);
            this.worldObj.createExplosion(this, this.posX + 10, this.posY - 10, this.posZ - 10, (float)this.explosionRadius * f, flag);
            this.worldObj.createExplosion(this, this.posX + 10, this.posY + 10, this.posZ - 10, (float)this.explosionRadius * f, flag);
            this.setHealth(this.getHealth() - 20);
            this.dataWatcher.updateObject(18, Byte.valueOf((byte)0));
        }
    }

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        return true;
    }
    public boolean func_146078_ca()
    {
        return this.dataWatcher.getWatchableObjectByte(18) != 0;
    }

    public void func_146079_cb()
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
    }
    public void func_175493_co()
    {
        ++this.field_175494_bm;
    }
    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float par1)
    {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * par1) / (float)(this.fuseTime - 2);
    }
}
