package com.BeRusted.ReactionElement.blocks;

import com.BeRusted.ReactionElement.ReactionElement;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class test_block extends Block {
    public test_block(){
        super(Material.GROUND, MapColor.DIRT);
        String block_name = "test_block";
        this.setRegistryName(block_name);
        this.setUnlocalizedName(ReactionElement.MODID+"."+block_name);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setHardness(0.5f);
        this.setResistance(10);
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(this);
    }
}
