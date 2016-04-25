package eu.ha3.matmos.game.data.modules;

import eu.ha3.matmos.engine.core.interfaces.Data;
import eu.ha3.matmos.game.data.abstractions.module.Module;
import eu.ha3.matmos.game.data.abstractions.module.ModuleProcessor;
import eu.ha3.matmos.game.system.MAtMod;
import eu.ha3.matmos.game.system.MAtmosUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

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
			setValue("id", calculateBiome().biomeID);
			setValue("biome_name", calculateBiome().biomeName);
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

        // dag edit - use BlockPos
//        BlockPos playerPos = MAtmosUtility.getPlayerPosition();
		int x = MathHelper.floor_double(mc.thePlayer.posX);
		int z = MathHelper.floor_double(mc.thePlayer.posZ);

//		Chunk chunk = mc.theWorld.getChunkFromBlockCoords(playerPos);
        // dag edit getBiomeGenForWorldCoords(..) -> getBiome(..)
//		return chunk.getBiome(playerPos, mc.theWorld.getWorldChunkManager());
		
		// Aussiemon edit 1.8 -> 1.7.10
		Chunk chunk = mc.theWorld.getChunkFromBlockCoords(x, z);
		return chunk.getBiomeGenForWorldCoords(x & 15, z & 15, mc.theWorld.getWorldChunkManager());
	}
}
