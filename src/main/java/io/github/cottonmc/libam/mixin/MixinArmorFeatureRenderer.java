package io.github.cottonmc.libam.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.cottonmc.libam.api.CustomArmorItem;
import io.github.cottonmc.libam.api.CustomArmorModel;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class MixinArmorFeatureRenderer<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {

	public MixinArmorFeatureRenderer(FeatureRendererContext<T, M> context) {
		super(context);
	}

	@Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
	public void renderCustomArmor(T living, float x, float y, float z, float float_4, float float_5, float float_6, float delta, EquipmentSlot slot, CallbackInfo info) {
		ItemStack stack = living.getEquippedStack(slot);
		if (!stack.isEmpty() && stack.getItem() instanceof CustomArmorItem) {
			CustomArmorItem armor = (CustomArmorItem) stack.getItem();
			GlStateManager.scalef(1.01F, 1.01F, 1.01F);

			if (living.isSneaking()) {
				GlStateManager.translated(0, 0.250D, 0);
			}

			CustomArmorModel<T> model = (CustomArmorModel<T>) armor.getArmorModel(stack);

			this.bindTexture(armor.getArmorTexture(stack));

			model.render(living, x, y, float_4, float_5, float_6, delta);
			GlStateManager.translatef(0, 0, 0F);
			GlStateManager.scalef(1F, 1F, 1F);
			info.cancel();
		}
	}
}
