import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SmartWorm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SmartWorm extends Wurm
{
    /**
     * Act - do whatever the SmartWorm wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
	public void act() 
	{
		if(seheTier(Krabbe.class, 80))// && random < 8)
		{
			setImage("wurmAbgetaucht.png");
			abgetaucht = true;
		}
		//aufjedenfall wieder auftauchen wenn keine krabben mehr in der naehe sind
		//if(seheTier(Krabbe.class, 80) == false)
		else
		{
			setImage("smartWorm.png");
			abgetaucht = false;
		}
	}
}
