package tk.cvrunmin.lanfasy.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCreeperBoss extends ModelBase
{
  //fields
    ModelRenderer ForeLeft;
    ModelRenderer ForeRight;
    ModelRenderer BackLeft;
    ModelRenderer BackRight;
    ModelRenderer Body;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer Out;
  
  public ModelCreeperBoss()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      ForeLeft = new ModelRenderer(this, 0, 16);
      ForeLeft.addBox(-2F, 0F, -2F, 4, 6, 4);
      ForeLeft.setRotationPoint(3F, 18F, -3F);
      ForeLeft.setTextureSize(64, 64);
      ForeLeft.mirror = true;
      setRotation(ForeLeft, 0F, 0F, 0F);
      ForeRight = new ModelRenderer(this, 0, 16);
      ForeRight.addBox(-2F, 0F, -2F, 4, 6, 4);
      ForeRight.setRotationPoint(-3F, 18F, -3F);
      ForeRight.setTextureSize(64, 64);
      setRotation(ForeRight, 0F, 0F, 0F);
      BackLeft = new ModelRenderer(this, 0, 16);
      BackLeft.addBox(-2F, 0F, -2F, 4, 6, 4);
      BackLeft.setRotationPoint(3F, 18F, 5F);
      BackLeft.setTextureSize(64, 64);
      BackLeft.mirror = true;
      setRotation(BackLeft, 0F, 0F, 0F);
      BackRight = new ModelRenderer(this, 0, 16);
      BackRight.addBox(-2F, 0F, -2F, 4, 6, 4);
      BackRight.setRotationPoint(-3F, 18F, 5F);
      BackRight.setTextureSize(64, 64);
      setRotation(BackRight, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 16, 16);
      Body.addBox(-5F, -3F, -6F, 10, 6, 12);
      Body.setRotationPoint(0F, 15F, 1F);
      Body.setTextureSize(64, 64);
      setRotation(Body, 0F, 0F, 0F);
      Neck = new ModelRenderer(this, 0, 26);
      Neck.addBox(-2F, -8F, -2F, 4, 8, 4);
      Neck.setRotationPoint(0F, 13F, -3F);
      Neck.setTextureSize(64, 64);
      setRotation(Neck, 0.3926991F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-4F, -4F, -4F, 8, 8, 8);
      Head.setRotationPoint(0F, 3F, -6F);
      Head.setTextureSize(64, 64);
      setRotation(Head, 0F, 0F, 0F);
      Out = new ModelRenderer(this, 32, 9);
      Out.addBox(-3F, 0F, -3F, 6, 1, 6);
      Out.setRotationPoint(0F, 11F, 3F);
      Out.setTextureSize(64, 64);
      setRotation(Out, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    ForeLeft.render(f5);
    ForeRight.render(f5);
    BackLeft.render(f5);
    BackRight.render(f5);
    Body.render(f5);
    Neck.render(f5);
    Head.render(f5);
    Out.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity6)
  {
      this.Head.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.Head.rotateAngleX = f4 / (180F / (float)Math.PI);
      this.Neck.rotateAngleY += f3 / (180F / (float)Math.PI);
      this.Neck.rotateAngleY -= f3 / (180F / (float)Math.PI);
      this.Neck.rotateAngleX += f4 / (180F / (float)Math.PI);
      this.Neck.rotateAngleX -= f4 / (180F / (float)Math.PI);
      this.ForeLeft.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.ForeRight.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
      this.BackLeft.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
      this.BackRight.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
  }

}
