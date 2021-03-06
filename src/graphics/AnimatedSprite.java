package graphics;

import java.awt.*;

import ui.Board;

/**
 * An AnimatedSprite is a Sprite based upon a .gif-File containing a collection of images, called frames. This way 
 * there can be various animations in the file. Though it is a collection of frames, the AnimatedSprite is still a Sprite and 
 * though represents only one image (one single frame) at a time, animation is realized by setting the index of the actual 
 * frame to different numbers at different points in time.
 * @author LionC
 */
public class AnimatedSprite extends Sprite {

	private int actualFrame = 0;
	private int frameLoopStart = -1;
	private int frameLoopEnd = -1;
	private Image[] frames;
	private static GifDecoder gifDecoder = new GifDecoder();

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        this.advanceFrame();
    }

    private void initializeData(String file) {
		if(AnimatedSprite.gifDecoder.read(file) == 2)
			System.err.println("Could not load file: " + file);
		frames = new Image[AnimatedSprite.gifDecoder.getFrameCount()];
		
		for(int i = 0; i < AnimatedSprite.gifDecoder.getFrameCount(); i++) {
			frames[i] =  AnimatedSprite.gifDecoder.getFrame(i);
		}

        this.frameLoopStart = 0;
        this.frameLoopEnd = frames.length - 1;
	}
	
	/**
	 * Construct a new AnimatedSprite
	 * @param file The path to the .gif File containing the frame data
	 * @param x X-Position
	 * @param y Y-Position
	 * @throws Exception
	 */
	public AnimatedSprite(String file, int x, int y) throws Exception {
		//TODO: AnimatedSprite FIles werden zweimal geladen (der Sprite Konstruktor l�dt auch)
		super(file, x, y);
		initializeData(file);
		this.setFrame(0);
	}

	/**
	 * Sets a new frame that this AnimatedSprite should be representing
	 * @param frame The frame index
	 */
	public void setFrame(int frame) {
		if(frame > frames.length)
			throw new IllegalArgumentException("Tried to use the " + frame + "-th frame, but only " +  frames.length + " frames are available.");
		
		this.image = this.frames[frame];
		this.actualFrame = frame;
	}
	
	/**
	 * Sets the frame loop of this AnimatedSprite, causing advanceFrame to cycle through this loop. Use -1 -1 for default loop
	 * @param start The start index of the loop
	 * @param end The end index of the loop
	 */
	public void setFrameLoop(int start, int end) {
		if(start < -1 || end < -1 || start >= this.frames.length || end >= this.frames.length)
			throw new IllegalArgumentException("Tried to set a frame Loop that is out of bounds of the available frames.");
		
		this.frameLoopStart = start;
		this.frameLoopEnd = end;
	}

	/**
	 * Advances a Frame in this AnimatedSprite, meaning it now represents the next frame. Automatically 
	 * loops through the actual frame loop.
	 */
	public void advanceFrame() {
		if(this.actualFrame == this.frameLoopEnd) 
			this.setFrame(this.frameLoopStart);
		else if(this.actualFrame == this.frames.length - 1)
			this.setFrame(0);
		else
			this.setFrame(this.actualFrame+1);
	}
}
