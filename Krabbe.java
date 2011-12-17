import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Diese Klasse beschreibt eine Krabbe. Diese leben am Strand und essen evtl. Wuermer.
 * 
 * @author B. Schnicke & A. Wiens :)
 */
public class Krabbe extends Tiere
{
	public boolean vorwaerts;
	public boolean satt;
	public boolean poisoned;
	public int gegessen;
	public boolean hasGoggles;

	private long apple_time = 0;
	private long poison_time = 0;
	private int step = 0;
	private static GreenfootImage crab1 = new GreenfootImage("crab.png");
	private static GreenfootImage crab2 = new GreenfootImage("crab2.png");
	private static GreenfootImage crab1Goggles = new GreenfootImage("crabGoggles.png");
	private static GreenfootImage crab2Goggles = new GreenfootImage("crab2Goggles.png");

	/**
	 * Hier legt ihr fest, was die Krabbe tuen soll.
	 */
	public Krabbe ()
	{
		vorwaerts = true;
		gegessen = 0;
		satt = false;
		poisoned = false;
		apple_time = System.currentTimeMillis();
		poison_time = System.currentTimeMillis();
		step = 0;
		hasGoggles = false;
	}

	public void act()
	{
		//randTestenUndDrehen();
		testeTasteGedrueckt();
		bewegen();
		suchUndEss();
		//richtungAendern(2);
		// zufallsDrehung(30);
	}

	/**
	 * Checkt ob ein tier (oder item) unter mir liegt, es also gesehen wird, und isst es.
	 */
	public void suchUndEss()
	{
		if(seheTier(Wurm.class, 20))
		{
			essen(Wurm.class, 20);
			gegessen++;
		}
		else if(seheTier(Apple.class, 20))
		{
			essen(Apple.class, 20);
			satt = true;
			apple_time = System.currentTimeMillis() + 1000;
		}
		else if(seheTier(Poison.class, 20))
		{
			essen(Poison.class, 20);
			poisoned = true;
			poison_time = System.currentTimeMillis() + 2000;
		}
		else if(seheTier(Goggles.class, 20))
		{
			essen(Goggles.class, 20);
			hasGoggles = true;
			setImage(crab1Goggles);
		}
	}

	/**
	 * Bewegt sich je nach wert der variable 'vorwaerts' nach vorwaerts oder rueckwaerts, und ueberprueft den aktuellen status z.B. ob der effekt des Apfels noch wirkt.
	 */
	public void bewegen() 
	{
		step ++;
		if(satt || poisoned)
		{
			if(satt)
			{
				//Benutzet System.currentTimeMillis(), da ein attribut das bei jedem aufruf inkrementiert wird (wie 'step') hat zumindest auf meinem system zu bugs/komischen ergebnissen gefuehrt
				if(apple_time < System.currentTimeMillis())
				{
					apple_time = 0;
					satt = false;
				}

				for(int i = 0; i < 4; i++)
				{
					if(vorwaerts == true)
						vw();
					else
						rw();
				}
			}
			if(poisoned)
			{
				drehe(10);
				if(poison_time < System.currentTimeMillis())
				{
					poison_time = 0;
					poisoned = false;
				}
			}
		}
		else
		{
			if(vorwaerts == true)
				vw();
			else
				rw();
		}

		if(step % 4 == 0 || step % 3 == 0)
			setImage((hasGoggles == false ? crab1 : crab1Goggles));
		else 
			setImage((hasGoggles == false ? crab2 : crab2Goggles));
	}

	/**
	 * Testet ob eine Taste die wir ueberwachen gedrueckt ist, und tut entsprechend etwas um die krabbe zu steuern.
	 */
	public void testeTasteGedrueckt()
	{
		if(Greenfoot.isKeyDown("left"))
			drehe(6);
		if(Greenfoot.isKeyDown("right"))
			drehe(-6);
		if(Greenfoot.isKeyDown("up"))
			vorwaerts = true;
		if(Greenfoot.isKeyDown("down"))
			vorwaerts = false;
	}

	/**
	 * Testet ob die Krabbe am rand ist, und dreht falls ja um +10*
	 */
	public void randTestenUndDrehen ()
	{
		if(amRand())
			drehe(10);
	}

	/**
	 * Dreht mit der angegebenen {@link wahrscheinlichkeit} um eine zufallszahl zwischen -20 und +20
	 * @param wahrscheinlichkeit
	 */
	public void zufallsDrehung (int wahrscheinlichkeit)
	{
		int random = (int)Math.round(Math.random() * 100);
		if(random < wahrscheinlichkeit)
		{
			drehe((int)Math.round(Math.random() * 40) - 20);
		}
	}

	/**
	 * Aendert mit der angegebenen {@link wahrscheinlichkeit} die richtung
	 * @param wahrscheinlichkeit
	 */
	public void richtungAendern (int wahrscheinlichkeit)
	{
		int random = (int)Math.round(Math.random() * 100);
		if(random < wahrscheinlichkeit)
		{
			vorwaerts = !vorwaerts;
			//drehe(180);
		}
	}
}