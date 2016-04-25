package eu.ha3.matmos.game.system;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.Map;

/*
--filenotes-placeholder
*/

public class SoundHelper implements SoundCapabilities
{
	protected SoundAccessor accessor;
	protected Map<String, NoAttenuationMovingSound> streaming;
	
	private float volumeModulator;
	
	private boolean isInterrupt;
	
	public SoundHelper(SoundAccessor accessor)
	{
		this.accessor = accessor;
		this.streaming = new LinkedHashMap<String, NoAttenuationMovingSound>();
	}

    public SoundManager getSoundManager()
    {
        return this.accessor.getSoundManager();
    }
	
	@Override
	public void playMono(String event, double xx, double yy, double zz, float volume, float pitch)
	{
		if (this.isInterrupt)
			return;
		
		playUnattenuatedSound(xx, yy, zz, event, volume * this.volumeModulator, pitch);
	}
	
	@Override
	public void playStereo(String event, float volume, float pitch)
	{
		if (this.isInterrupt)
			return;
		
		// Play the sound 2048 blocks above the player to keep support of mono sounds
		Entity e = Minecraft.getMinecraft().thePlayer;
		playUnattenuatedSound(e.posX, e.posY + 2048, e.posZ, event, volume * this.volumeModulator, pitch);
	}
	
	private void playUnattenuatedSound(double xx, double yy, double zz, String loc, float volume, float pitch)
	{
		NoAttenuationSound nas = new NoAttenuationSound(new ResourceLocation(loc), volume, pitch, (float) xx, (float) yy, (float) zz);
		
		Minecraft.getMinecraft().getSoundHandler().playSound(nas);
		
	}
	
	@Override
	public void registerStreaming(
		String customName, String path, float volume, float pitch, boolean isLooping, boolean usesPause)
	{
		if (this.isInterrupt)
			return;

		String loc = path.replace(".ogg", "").replace('/', '.').replaceAll("[0-9]", "");
		NoAttenuationMovingSound nams = new NoAttenuationMovingSound(new ResourceLocation(loc), volume, pitch, isLooping, usesPause);

		this.streaming.put(customName, nams);
	}
	
	@Override
	public void playStreaming(String customName, float fadeIn)
	{
		if (this.isInterrupt)
			return;
		
		if (!this.streaming.containsKey(customName))
		{
			IDontKnowHowToCode.warnOnce("Tried to play missing stream " + customName);
			return;
		}

		// Ensure previous sound is disposed of
		this.streaming.get(customName).dispose();

		NoAttenuationMovingSound copy = this.streaming.get(customName).copy();
		this.streaming.put(customName, copy);
        copy.play(fadeIn);
		Minecraft.getMinecraft().getSoundHandler().playSound(copy);
	}
	
	@Override
	public void stopStreaming(String customName, float fadeOut)
	{
		if (this.isInterrupt)
			return;
		
		if (!this.streaming.containsKey(customName))
		{
			IDontKnowHowToCode.warnOnce("Tried to stop missing stream " + customName);
			return;
		}

        this.streaming.get(customName).stop(fadeOut);
	}
	
	@Override
	public void stop()
	{
		if (this.isInterrupt)
			return;
		
		for (StreamingSound sound : this.streaming.values())
		{
			sound.dispose();
		}
	}
	
	@Override
	public void applyVolume(float volumeMod)
	{
		this.volumeModulator = volumeMod;
        for (StreamingSound sound : this.streaming.values())
        {
            sound.applyVolume(volumeMod);
        }
	}
	
	@Override
	public void interrupt()
	{
		this.isInterrupt = true;
	}
	
	@Override
	public void cleanUp()
	{
		if (this.isInterrupt)
			return;
		
		for (StreamingSound sound : this.streaming.values())
		{
			sound.dispose();
		}
		this.streaming.clear();
	}
	
}
