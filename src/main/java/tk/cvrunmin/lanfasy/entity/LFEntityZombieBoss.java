package tk.cvrunmin.lanfasy.entity;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LFEntityZombieBoss extends EntityMob implements IBossDisplayData{
    protected static final IAttribute reinforcementChance = (new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
    private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
    private final EntityAIBreakDoor breakDoor = new EntityAIBreakDoor(this);
    private int conversionTime;
    private boolean field_146076_bu = false;
    private float zombieWidth = -1.0F;
    private float zombieHeight;
	public LFEntityZombieBoss(World worldIn) {
		super(worldIn);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(2, this.aiAvoidExplodingCreepers);
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
        this.setSize(0.6F, 1.95F);
	}
    protected void applyEntityAI()
    {
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityIronGolem.class, 1.0D, true));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 5.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {LFEntityZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000D);
        this.getAttributeMap().registerAttribute(reinforcementChance).setBaseValue(this.rand.nextDouble() * net.minecraftforge.common.ForgeModContainer.zombieSummonBaseChance);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(12, Byte.valueOf((byte)0));
        this.getDataWatcher().addObject(13, Byte.valueOf((byte)0));
        this.getDataWatcher().addObject(14, Byte.valueOf((byte)0));
    }
    public int getTotalArmorValue()
    {
        int i = super.getTotalArmorValue() + 2;
        if (i > 20)
        {
            i = 20;
        }
        return i;
    }
    public boolean func_146072_bX()
    {
        return this.field_146076_bu;
    }
    public void func_146070_a(boolean p_146070_1_)
    {
        if (this.field_146076_bu != p_146070_1_)
        {
            this.field_146076_bu = p_146070_1_;

            if (p_146070_1_)
            {
                this.tasks.addTask(1, this.breakDoor);
            }
            else
            {
                this.tasks.removeTask(this.breakDoor);
            }
        }
    }
    public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
        {
            float f = this.getBrightness(1.0F);
            BlockPos blockpos = new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canSeeSky(blockpos))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, (ItemStack)null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
//                	this.worldObj.setBlockState(new BlockPos(this.posX, this.posY + 5, this.posZ), Blocks.stone.getDefaultState(), 2);
                }
            }
        }

        if (this.isRiding() && this.getAttackTarget() != null && this.ridingEntity instanceof EntityChicken)
        {
            ((EntityLiving)this.ridingEntity).getNavigator().setPath(this.getNavigator().getPath(), 1.5D);
        }

        super.onLivingUpdate();
    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (super.attackEntityFrom(source, amount))
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && source.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)source.getEntity();
            }

            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        boolean flag = super.attackEntityAsMob(p_70652_1_);

        if (flag)
        {
            int i = this.worldObj.getDifficulty().getDifficultyId();

            if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)i * 0.3F)
            {
                p_70652_1_.setFire(2 * i);
            }
        }

        return flag;
    }
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setBoolean("CanBreakDoors", this.func_146072_bX());
    }

    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.func_146070_a(tagCompund.getBoolean("CanBreakDoors"));
    }
    public void onKillEntity(EntityLivingBase entityLivingIn)
    {
        super.onKillEntity(entityLivingIn);

        if ((this.worldObj.getDifficulty() == EnumDifficulty.NORMAL || this.worldObj.getDifficulty() == EnumDifficulty.HARD) && entityLivingIn instanceof EntityVillager)
        {
            if (this.worldObj.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean())
            {
                return;
            }

            LFEntityZombie entityzombie = new LFEntityZombie(this.worldObj);
            entityzombie.copyLocationAndAnglesFrom(entityLivingIn);
            this.worldObj.removeEntity(entityLivingIn);
            entityzombie.onInitialSpawn(this.worldObj.getDifficultyForLocation(new BlockPos(entityzombie)), (IEntityLivingData)null);
            entityzombie.setVillager(true);

            if (entityLivingIn.isChild())
            {
                entityzombie.setChild(true);
            }

            this.worldObj.spawnEntityInWorld(entityzombie);
            this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
        }
    }
    protected boolean func_175448_a(ItemStack p_175448_1_)
    {
        return p_175448_1_.getItem() == Items.egg && this.isChild() && this.isRiding() ? false : super.func_175448_a(p_175448_1_);
    }
    public IEntityLivingData onInitialSpawn(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
    {
        Object p_180482_2_1 = super.onInitialSpawn(p_180482_1_, p_180482_2_);
        float f = p_180482_1_.getClampedAdditionalDifficulty();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

        if (p_180482_2_1 == null)
        {
            p_180482_2_1 = new LFEntityZombieBoss.GroupData(this.worldObj.rand.nextFloat() < net.minecraftforge.common.ForgeModContainer.zombieBabyChance, this.worldObj.rand.nextFloat() < 0.05F, null);
        }

        if (p_180482_2_1 instanceof LFEntityZombieBoss.GroupData)
        {
            LFEntityZombieBoss.GroupData groupdata = (LFEntityZombieBoss.GroupData)p_180482_2_1;
        }

        this.func_146070_a(this.rand.nextFloat() < f * 0.1F);
        this.setEquipmentBasedOnDifficulty(p_180482_1_);
        this.setEnchantmentBasedOnDifficulty(p_180482_1_);

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.rand.nextDouble() * 1.5D * (double)f;

        if (d0 > 1.0D)
        {
            this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }

        if (this.rand.nextFloat() < f * 0.05F)
        {
            this.getEntityAttribute(reinforcementChance).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
            this.func_146070_a(true);
        }

        return (IEntityLivingData)p_180482_2_1;
    }
    protected final void setSize(float width, float height)
    {
        boolean flag = this.zombieWidth > 0.0F && this.zombieHeight > 0.0F;
        this.zombieWidth = width;
        this.zombieHeight = height;

        if (!flag)
        {
            this.multiplySize(1.0F);
        }
    }
    public double getYOffset()
    {
        return super.getYOffset() - 0.5D;
    }
    protected final void multiplySize(float size)
    {
        super.setSize(this.zombieWidth * size, this.zombieHeight * size);
    }
    class GroupData implements IEntityLivingData
    {
        public boolean field_142048_a;
        public boolean field_142046_b;

        private GroupData(boolean par2, boolean par3)
        {
            this.field_142048_a = false;
            this.field_142046_b = false;
            this.field_142048_a = par2;
            this.field_142046_b = par3;
        }

        GroupData(boolean par2, boolean par3, Object par4EntityZombieINNER1)
        {
            this(par2, par3);
        }
    }
}
