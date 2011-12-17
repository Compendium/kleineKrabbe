import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ein Sandstrand f�r allerlei Getier
 * 
 * @author B. Schnicke
 * @version 1.0
 */
public class StrandWelt  extends World
{
	GreenfootImage bg;
	GreenfootImage scorebg;
	int score = 0;
	GreenfootSound bgMusic;
	Krabbe crab;
	/**
	 * Erzeugt einen Strand. Er hat eine Groe�e von 600x600 Zellen, jede 
	 * Zelle ist ein Pixel gross.
	 * 
	 */
	public StrandWelt()
	{
		super(600, 600, 1);
		bg = new GreenfootImage("sand.jpg");
		scorebg = bg;
		setBackground(scorebg);
		//bgMusic = new GreenfootSound("bgMusic.wav");
		for(int i = 0; i < 5; i++)
		{
			addObject(new Wurm(), (int)Math.round(Math.random() * 600), (int)Math.round(Math.random() * 590) + 10);
		}
		for(int i = 0; i < (int)Math.round(Math.random() * 20); i++)
		{
			addObject(new SmartWorm(), (int)Math.round(Math.random() * 600), (int)Math.round(Math.random() * 590) + 10);
		}

		crab = new Krabbe();
		addObject(crab, (int)Math.round(Math.random() * 600), (int)Math.round(Math.random() * 600));
		addObject(new Goggles(), (int)Math.round(Math.random() * 600), (int)Math.round(Math.random() * 600));

		for(int i = 0; i < 1; i++)
		{
			addObject(new Poison(), (int)Math.round(Math.random() * 600), (int)Math.round(Math.random() * 600));
		}

		for(int i = 0; i < 2; i++)
		{
			addObject(new Apple(), (int)Math.round(Math.random() * 600), (int)Math.round(Math.random() * 600));
		}
	}

	public void act ()
	{
		if(score != crab.gegessen)
		{
			score = crab.gegessen;
			scorebg = new GreenfootImage(bg); //reset background
			scorebg.drawString("P:" + score, 0, 10);
			setBackground(scorebg);
		}
	}
}