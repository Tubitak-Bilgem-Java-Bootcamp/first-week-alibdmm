public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean water;
    private boolean food;
    private boolean firewoord;

    public Inventory() {
        this.weapon = new Weapon("Yumruk",-1,0,0);
        this.armor = new Armor(-1, "Üniforma", 0,0);
        this.food = false;
        this.water = false;
        this.firewoord = false;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewoord() {
        return firewoord;
    }

    public void setFirewoord(boolean firewoord) {
        this.firewoord = firewoord;
    }
}
