package eu.ha3.matmos.game.data.modules;

import eu.ha3.matmos.engine.core.interfaces.Data;
import eu.ha3.matmos.game.data.abstractions.module.Module;
import eu.ha3.matmos.game.data.abstractions.module.ModuleProcessor;
import eu.ha3.matmos.game.system.MAtMod;
import eu.ha3.matmos.game.system.MAtmosUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.util.MathHelper;

/*
--filenotes-placeholder
*/

public class M__w_biome extends ModuleProcessor implements Module
{
	private final MAtMod mod;
	
	public M__w_biome(Data data, MAtMod mod)
	{
		super(data, "w_biome");
		this.mod = mod;
	}
	
	@Override
	protected void doProcess()
	{
		int biomej = this.mod.getConfig().getInteger("useroptions.biome.override");
		if (biomej <= -1)
		{
			//Solly edit - only calculate biome once
			BiomeGenBase biome = calculateBiome();
			setValue("id", biome.biomeID);
			setValue("biome_name", biome.biomeName);
		}
		else
		{
			setValue("id", biomej);
			setValue("biome_name", "");
		}
	}
	
	private BiomeGenBase calculateBiome()
	{
		Minecraft mc = Minecraft.getMinecraft();
		int x = MathHelper.floor_double(mc.thePlayer.posX);
		int z = MathHelper.floor_double(mc.thePlayer.posZ);

		Chunk chunk = mc.theWorld.getChunkFromBlockCoords(x, z);
        // dag edit getBiomeGenForWorldCoords(..) -> getBiome(..)
		return chunk.getBiomeGenForWorldCoords(x & 15, z & 15, mc.theWorld.getWorldChunkManager());
	}
}
