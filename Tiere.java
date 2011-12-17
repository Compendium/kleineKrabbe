import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Dies ist die Grundlage f�r alle Tierarten. Es enth�lt Methoden zum laufen, drehen
 * un essen.
 * 
 * @author B. Schnicke
 * @version 1.0
 */
public class Tiere  extends Actor
{   
    private static final double LAUFGESCHWINDIGKEIT = 5.0;

    /**
     * Constructor f�r Tiere - macht nichts
     */
    public Tiere()
    {
    }

    /**
     * Leere Methode die in den Unterklassen ueberschrieben werden soll.
     */
    public void act() 
    {
        // Add your action code here.
    }

    /**
     * Dreht das Tier um den angegebenen Winkel im Uhrzeigersinn
     * @param winkel
     */
    public void drehe(int winkel)
    {
        setRotation(getRotation() + winkel);
    }

    /**
     * Bewegt das Tier in die angegebene Richtung vorwaerts.
     */
    public void vw()
    {
        double winkel = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(winkel) * LAUFGESCHWINDIGKEIT);
        int y = (int) Math.round(getY() + Math.sin(winkel) * LAUFGESCHWINDIGKEIT);

        setLocation(x, y);
    }

    /**
     * Bewegt das Tier in die angegebene Richtung rueckwaerts.
     */
    public void rw()
    {
        double winkel = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() - Math.cos(winkel) * LAUFGESCHWINDIGKEIT);
        int y = (int) Math.round(getY() - Math.sin(winkel) * LAUFGESCHWINDIGKEIT);

        //setLocation(getX() - (x - getX()), getY() - (y - getY()));
        setLocation(x, y);
    }

    /**
     * Testet ob ein anderes Tier der Klasse "tierart" direkt vor uns steht.
     * @param tierart Die Tierart nach der gesucht wird
     * @return true falls ein Tier der angegebenen Art direkt vor einem steht.
     */
    public boolean seheTier(Class tierart)
    {
        Actor tier = getOneObjectAtOffset(0, 0, tierart);
        //wenn das tier existiert, wenn es ein wurm ist, und wenn dieser wurm 'hidden' ist -> return false
        if(tier != null && (tierart == Wurm.class || tierart == SmartWorm.class) && ((Wurm)tier).istAbgetaucht() == false)
            return true;
        return (tier != null);    
    }

    public boolean seheTier(Class tierart, int range)
    {
        /*java.util.List<Actor> l = getObjectsInRange(range, tierart);
        if(l.size() > 0)
            return true;
        return false;*/
        java.util.List<Actor> l = getObjectsInRange(range, tierart);
        if(l.size() > 0)
                {
                    if(this == Krabbe.class)
                        if(((Krabbe)this).hasGoggles)
                            return true;
                    //wenn das tier existiert, wenn es ein wurm ist, und wenn dieser wurm 'hidden' ist -> return false
                    if(l.get(0) != null && (tierart == Wurm.class || tierart == SmartWorm.class) && ((Wurm)l.get(0)).istAbgetaucht() == true)
                        return false;
                    return true;
                }
                return false;
        }

        /**
         * Isst ein Tier der angegebenen Tierart auf, wenn es sich direkt vor einem befindet.
         * @param tierart Das Tier das gegessen werden soll
         */
        public void essen(Class tierart)
        {
            Actor tier = getOneObjectAtOffset(0, 0, tierart);
            if(tierart != null)
            {
                getWorld().removeObject(tier);
                if(tierart == Wurm.class)
                {
                    ((Wurm)tier).die();
                }
            }
            /*
            java.util.List<Actor> l = getObjectsInRange(range, tierart);
            if(l.size() > 0)
            {
                getWorld().removeObject(l[0]);
                if(tierart == Wurm.class)
                {
                    ((Wurm)tier).die();
                }
            }*/
        }

        /**
         * Isst ein Tier der angegebenen Tierart auf, wenn es sich direkt vor einem befindet.
         * @param tierart Das Tier das gegessen werden soll
         */
        public void essen(Class tierart, int range)
        {
            java.util.List<Actor> l = getObjectsInRange(range, tierart);
            if(l.size() > 0)
            {
                getWorld().removeObject(l.get(0));
                if(tierart == Wurm.class)
                {
                    ((Wurm)l.get(0)).die();
                }
            }
        }

        /**
         * Testet ob das Tier am Rand der Umgebung angekommen ist.
         *
         * @return     true - Das Tier ist weniger als 20 Pixel vom Rand entfernt.
         */
        public boolean amRand()
        {  boolean amRandAngekommen = false;
            if(getX() < 20 || getX() > getWorld().getWidth() - 20)
                amRandAngekommen = true;
            if(getY() < 20 || getY() > getWorld().getHeight() - 20)
                amRandAngekommen = true;
            return amRandAngekommen;
        }
}
