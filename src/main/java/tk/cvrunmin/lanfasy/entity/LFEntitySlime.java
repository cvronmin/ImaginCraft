package tk.cvrunmin.lanfasy.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class LFEntitySlime extends EntityLiving implements IMob{
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private boolean field_175452_bi;
	public Object instance;
	public static int mobid = 1014;
	int entityID = mobid;
	@SuppressWarnings("unchecked")
	public void preInit(FMLPreInitializationEvent event){
		EntityRegistry.registerGlobalEntityID(LFEntitySlime.class, "Slime(ML)", entityID);
		EntityRegistry.registerModEntity(LFEntitySlime.class, "Slime(ML)", entityID, instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID,  5349438, 8306542));        
	}
	public LFEntitySlime(World par1World) {
		super(par1World);
        this.moveHelper = new LFEntitySlime.SlimeMoveHelper();
        this.tasks.addTask(1, new LFEntitySlime.AISlimeFloat());
        this.tasks.addTask(2, new LFEntitySlime.AISlimeAttack());
        this.tasks.addTask(3, new LFEntitySlime.AISlimeFaceRandom());
        this.tasks.addTask(5, new LFEntitySlime.AISlimeHop());
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
	}
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)1));
    }
    protected void setSlimeSize(int p_70799_1_)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)p_70799_1_));
        this.setSize(0.51000005F * (float)p_70799_1_, 0.51000005F * (float)p_70799_1_);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(p_70799_1_ * p_70799_1_));
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)(0.2F + 0.1F * (float)p_70799_1_));
        this.setHealth(this.getMaxHealth());
        this.experienceValue = p_70799_1_;
    }

    public int getSlimeSize()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("Size", this.getSlimeSize() - 1);
        tagCompound.setBoolean("wasOnGround", this.field_175452_bi);
    }

    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        int i = tagCompund.getInteger("Size");

        if (i < 0)
        {
            i = 0;
        }

        this.setSlimeSize(i + 1);
        this.field_175452_bi = tagCompund.getBoolean("wasOnGround");
    }

    protected String getSlimeParticle()
    {
        return "slime";
    }
    protected EnumParticleTypes func_180487_n()
    {
        return EnumParticleTypes.SLIME;
    }

    protected String getJumpSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }
    public void onUpdate()
    {
        if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0)
        {
            this.isDead = true;
        }

        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        super.onUpdate();

        if (this.onGround && !this.field_175452_bi)
        {
            int i = this.getSlimeSize();

            for (int j = 0; j < i * 8; ++j)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                World world = this.worldObj;
                EnumParticleTypes enumparticletypes = this.func_180487_n();
                double d0 = this.posX + (double)f2;
                double d1 = this.posZ + (double)f3;
                world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
            }

            if (this.makesSoundOnLand())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && this.field_175452_bi)
        {
            this.squishAmount = 1.0F;
        }

        this.field_175452_bi = this.onGround;
        this.alterSquishAmount();
    }
    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.6F;
    }

    protected int getJumpDelay()
    {
        return this.rand.nextInt(20) + 10;
    }

    protected LFEntitySlime createInstance()
    {
        return new LFEntitySlime(this.worldObj);
    }
    public void onDataWatcherUpdate(int p_145781_1_)
    {
        if (p_145781_1_ == 16)
        {
            int j = this.getSlimeSize();
            this.setSize(0.51000005F * (float)j, 0.51000005F * (float)j);
            this.rotationYaw = this.rotationYawHead;
            this.renderYawOffset = this.rotationYawHead;

            if (this.isInWater() && this.rand.nextInt(20) == 0)
            {
                this.resetHeight();
            }
        }

        super.onDataWatcherUpdate(p_145781_1_);
    }
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            int j = 2 + this.rand.nextInt(3);

            for (int k = 0; k < j; ++k)
            {
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                LFEntitySlime entityslime = this.createInstance();

                if (this.hasCustomName())
                {
                    entityslime.setCustomNameTag(this.getCustomNameTag());
                }

                if (this.isNoDespawnRequired())
                {
                    entityslime.enablePersistence();
                }

                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entityslime);
            }
        }

        super.setDead();
    }
    public void applyEntityCollision(Entity entityIn)
    {
        super.applyEntityCollision(entityIn);

        if (entityIn instanceof EntityIronGolem && this.canDamagePlayer())
        {
            this.func_175451_e((EntityLivingBase)entityIn);
        }
    }
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if (this.canDamagePlayer())
        {
            this.func_175451_e(entityIn);
        }
    }

    protected void func_175451_e(EntityLivingBase p_175451_1_)
    {
        int i = this.getSlimeSize();

        if (this.canEntityBeSeen(p_175451_1_) && this.getDistanceSqToEntity(p_175451_1_) < 0.6D * (double)i * 0.6D * (double)i && p_175451_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength()))
        {
            this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.applyEnchantments(this, p_175451_1_);
        }
    }
    public float getEyeHeight()
    {
        return 0.625F * this.height;
    }

    protected boolean canDamagePlayer()
    {
        return this.getSlimeSize() > 1;
    }

    protected int getAttackStrength()
    {
        return this.getSlimeSize();
    }

    protected String getHurtSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    protected String getDeathSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    protected Item getDropItem()
    {
//        return this.getSlimeSize() == 1 ? MLRItems.rSlime_ball : Item.getItemById(0);
    	return null;
    }
    public boolean getCanSpawnHere()
    {
        Chunk chunk = this.worldObj.getChunkFromBlockCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)));

        if (this.worldObj.getWorldInfo().getTerrainType().handleSlimeSpawnReduction(rand, worldObj))
        {
            return false;
        }
        else
        {
            if (this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL)
            {
                BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)));

                if (biomegenbase == BiomeGenBase.swampland && this.posY > 50.0D && this.posY < 70.0D && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < this.worldObj.getCurrentMoonPhaseFactor() && this.worldObj.getLightFromNeighbors(new BlockPos(this)) <= this.rand.nextInt(8))
                {
                    return super.getCanSpawnHere();
                }

                if (this.rand.nextInt(10) == 0 && chunk.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D)
                {
                    return super.getCanSpawnHere();
                }
            }

            return false;
        }
    }
    protected float getSoundVolume()
    {
        return 0.4F * (float)this.getSlimeSize();
    }
    public int getVerticalFaceSpeed()
    {
        return 0;
    }
    protected boolean makesSoundOnJump()
    {
        return this.getSlimeSize() > 0;
    }
    protected boolean makesSoundOnLand()
    {
        return this.getSlimeSize() > 2;
    }
    protected void jump()
    {
        this.motionY = 0.41999998688697815D;
        this.isAirBorne = true;
    }
    public IEntityLivingData onInitialSpawn(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
    {
        int i = this.rand.nextInt(3);

        if (i < 2 && this.rand.nextFloat() < 0.5F * p_180482_1_.getClampedAdditionalDifficulty())
        {
            ++i;
        }

        int j = 1 << i;
        this.setSlimeSize(j);
        return super.onInitialSpawn(p_180482_1_, p_180482_2_);
    }
    class AISlimeAttack extends EntityAIBase
    {
        private LFEntitySlime field_179466_a = LFEntitySlime.this;
        private int field_179465_b;

        public AISlimeAttack()
        {
            this.setMutexBits(2);
        }

        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.field_179466_a.getAttackTarget();
            return entitylivingbase == null ? false : entitylivingbase.isEntityAlive();
        }

        public void startExecuting()
        {
            this.field_179465_b = 300;
            super.startExecuting();
        }

        public boolean continueExecuting()
        {
            EntityLivingBase entitylivingbase = this.field_179466_a.getAttackTarget();
            return entitylivingbase == null ? false : (!entitylivingbase.isEntityAlive() ? false : --this.field_179465_b > 0);
        }

        public void updateTask()
        {
            this.field_179466_a.faceEntity(this.field_179466_a.getAttackTarget(), 10.0F, 10.0F);
            ((LFEntitySlime.SlimeMoveHelper)this.field_179466_a.getMoveHelper()).func_179920_a(this.field_179466_a.rotationYaw, this.field_179466_a.canDamagePlayer());
        }
    }

    class AISlimeFaceRandom extends EntityAIBase
    {
        private LFEntitySlime field_179461_a = LFEntitySlime.this;
        private float field_179459_b;
        private int field_179460_c;

        public AISlimeFaceRandom()
        {
            this.setMutexBits(2);
        }

        public boolean shouldExecute()
        {
            return this.field_179461_a.getAttackTarget() == null && (this.field_179461_a.onGround || this.field_179461_a.isInWater() || this.field_179461_a.isInLava());
        }

        public void updateTask()
        {
            if (--this.field_179460_c <= 0)
            {
                this.field_179460_c = 40 + this.field_179461_a.getRNG().nextInt(60);
                this.field_179459_b = (float)this.field_179461_a.getRNG().nextInt(360);
            }

            ((LFEntitySlime.SlimeMoveHelper)this.field_179461_a.getMoveHelper()).func_179920_a(this.field_179459_b, false);
        }
    }

    class AISlimeFloat extends EntityAIBase
    {
        private LFEntitySlime field_179457_a = LFEntitySlime.this;

        public AISlimeFloat()
        {
            this.setMutexBits(5);
            ((PathNavigateGround)LFEntitySlime.this.getNavigator()).setCanSwim(true);
        }

        public boolean shouldExecute()
        {
            return this.field_179457_a.isInWater() || this.field_179457_a.isInLava();
        }

        public void updateTask()
        {
            if (this.field_179457_a.getRNG().nextFloat() < 0.8F)
            {
                this.field_179457_a.getJumpHelper().setJumping();
            }

            ((LFEntitySlime.SlimeMoveHelper)this.field_179457_a.getMoveHelper()).func_179921_a(1.2D);
        }
    }

    class AISlimeHop extends EntityAIBase
    {
        private LFEntitySlime field_179458_a = LFEntitySlime.this;
        public AISlimeHop()
        {
            this.setMutexBits(5);
        }

        public boolean shouldExecute()
        {
            return true;
        }

        public void updateTask()
        {
            ((LFEntitySlime.SlimeMoveHelper)this.field_179458_a.getMoveHelper()).func_179921_a(1.0D);
        }
    }

    class SlimeMoveHelper extends EntityMoveHelper
    {
        private float field_179922_g;
        private int field_179924_h;
        private LFEntitySlime field_179925_i = LFEntitySlime.this;
        private boolean field_179923_j;

        public SlimeMoveHelper()
        {
            super(LFEntitySlime.this);
        }

        public void func_179920_a(float p_179920_1_, boolean p_179920_2_)
        {
            this.field_179922_g = p_179920_1_;
            this.field_179923_j = p_179920_2_;
        }

        public void func_179921_a(double p_179921_1_)
        {
            this.speed = p_179921_1_;
            this.update = true;
        }

        public void onUpdateMoveHelper()
        {
            this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.field_179922_g, 30.0F);
            this.entity.rotationYawHead = this.entity.rotationYaw;
            this.entity.renderYawOffset = this.entity.rotationYaw;

            if (!this.update)
            {
                this.entity.setMoveForward(0.0F);
            }
            else
            {
                this.update = false;

                if (this.entity.onGround)
                {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));

                    if (this.field_179924_h-- <= 0)
                    {
                        this.field_179924_h = this.field_179925_i.getJumpDelay();

                        if (this.field_179923_j)
                        {
                            this.field_179924_h /= 3;
                        }

                        this.field_179925_i.getJumpHelper().setJumping();

                        if (this.field_179925_i.makesSoundOnJump())
                        {
                            this.field_179925_i.playSound(this.field_179925_i.getJumpSound(), this.field_179925_i.getSoundVolume(), ((this.field_179925_i.getRNG().nextFloat() - this.field_179925_i.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    }
                    else
                    {
                        this.field_179925_i.moveStrafing = this.field_179925_i.moveForward = 0.0F;
                        this.entity.setAIMoveSpeed(0.0F);
                    }
                }
                else
                {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
                }
            }
        }
    }
}
