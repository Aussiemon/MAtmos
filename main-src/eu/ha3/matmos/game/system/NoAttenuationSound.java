package eu.ha3.matmos.game.system;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

/*
--filenotes-placeholder
*/

public class NoAttenuationSound extends PositionedSoundRecord
{
	public NoAttenuationSound(ResourceLocation loc, float volume, float pitch, float x, float y, float z)
	{
		super(loc, volume, pitch, x, y, z);
		this.attenuationType = ISound.AttenuationType.NONE;
	}
}
