package sound;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class representing a single Sound, being bound to a Sound File.
 * The Class also acts as kind of a Sound-Manager, as more instances of a Sound-manager per application make no sense
 * @author LionC
 *
 */
public class Sound {
	/**
	 * The central place where all Sounds are stored indexed by their given name
	 */
	private static HashMap<String, Sound> sounds = new HashMap<>();
	/**
	 * The sound filepaths that are already loaded as a Sound
	 */
	private static HashSet<String> filenames = new HashSet<>();
	
	/**
	 * Loads a new Sound
	 * @param file The path to the file containing the audio data
	 * @param name The name of the new Sound
	 * @return The new Sound, null if not successfull
	 */
	public static Sound loadSound(String file, String name) {
		Sound theNewSound = new Sound(file);
		
		if(!addSound(theNewSound, name))
			theNewSound = null;
		
		return theNewSound;
	}
	
	/**
	 * Adds a Sound to the managed Sounds giving it a name
	 * @param sound The Sound to be added
	 * @param name The name for that Sound
	 * @return True if successfull, false if not
	 */
	public static boolean addSound(Sound sound, String name) {
		if(!(sounds.put(name, sound) == null && filenames.add(sound.filePath))) {
			sounds.remove(sound);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Plays an already loaded Sound by name
	 * @param name The name of the Sound that should be played
	 */
	public static void play(String name) {
		Sound theSound = Sound.sounds.get(name);
		if(theSound != null) {
			theSound.play();
		}
	}
	
	private AudioInputStream stream;
	private String filePath;
	protected List<Clip> clips = new LinkedList<>();
	
	/**
	 * Constructs a new Sound Object without a name
	 * @param file The path to the file containing the audio data
	 */
	public Sound(String file) {
		Clip clip = null;
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.stream = AudioSystem.getAudioInputStream(new File(file));
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Tried to load an audio file with an unsupported format: " + file);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try {
			clip.open(this.stream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		
		this.filePath = file;
		this.clips.add(clip);
	}
	
	/**
	 * Plays this Sound
	 */
	public void play() {
		this.getFreeClip().start();
	}
	
	protected Clip getFreeClip() {
		//Check if there is a clip that is not playing...
		for(Clip act : this.clips) {
			if(!act.isRunning()) {
				return act;
			}
		}
		
		//...if not create a new Clip, add it and return it
		
		Clip newClip = null;
		
		try {
			newClip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		try {
			newClip.open(stream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
			
		this.clips.add(newClip);
		return newClip;
	}
	
}
