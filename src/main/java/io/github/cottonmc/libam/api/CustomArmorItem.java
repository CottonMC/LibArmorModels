package io.github.cottonmc.libam.api;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public abstract class CustomArmorItem extends ArmorItem {

	public CustomArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	public abstract CustomArmorModel<?> getArmorModel(ItemStack stack);

	public abstract Identifier getArmorTexture(ItemStack stack);
}
