package Droid;

public class Droid {
    protected String name;
    protected int health;
    protected int damage;
    protected int initialHealth;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.initialHealth = health;
    }
    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }

    public int getDamage()
    {
        return damage;
    }

    public void takeDamage(int damage)
    {
        this.health -= damage;
    }

    public boolean isAlive()
    {
        return health >0;
    }

    public int attack()
    {
        return damage;
    }

    public String toString()
    {
        return "Droid: "+ name + "[health =" + health + ", damage = "+ damage +"]";
    }

    public void resetHealth() {
        this.health = initialHealth;
    }

public static class AttackDriod extends Droid
{

    public AttackDriod(String name)
    {
        super(name, 100, 20);
    }

 }

public static class DefenseDriod extends Droid
{

     public DefenseDriod(String name)
     {
         super(name, 150, 10);
     }

 }

}
