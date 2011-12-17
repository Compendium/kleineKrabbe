import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wurm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wurm extends Tiere
{
    int time = 0;
    public boolean abgetaucht = false;
		static GreenfootImage img_abgetaucht = new GreenfootImage("wurmAbgetaucht.png");
		static GreenfootImage img_aufgetaucht = new GreenfootImage("worm.png");
		GreenfootSound deathSound;

		public Wurm ()
		{
			deathSound = new GreenfootSound("meinBein.mp3");
			setImage(img_aufgetaucht);
		}

    /**
     * Act - do whatever the Wurm wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {       
        //hidden = true;
        //setImage("wurmAbgetaucht.png");
        
        time++;
        if(time > 20)
        {
            time = 0;
            int random = (int)Math.round(Math.random() * 100);
            if(random > 80)
            {
                setImage(img_abgetaucht);
                abgetaucht = true;
            }
            if(random < 80)
            {
                setImage(img_aufgetaucht);
                abgetaucht = false;
            }
        }
    }   
   
		public void die ()
		{
			//deathSound.play();
		}

    /**
     * Gibt wieder ob der wurm abgetaucht ist
     * @return     true wenn wurm abgetaucht ist
     */
    public boolean istAbgetaucht()
    {
        return abgetaucht;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * @param  wahrscheinlichkeit die wahrscheinlichkeit mit der der wurm etwas tuen soll
     * @return     nothing
     */
    public void abtauchenOderAuftauchen(int wahrscheinlichkeit)
    {
        int random = (int)Math.round(Math.random() * 100);
        if(random < wahrscheinlichkeit)
        {
            abgetaucht = !abgetaucht;
            if(abgetaucht)
                setImage(img_abgetaucht);
            else
                setImage(img_aufgetaucht);
        }
    }
}
