import java.util.Arrays;

public class Weapon {
    private int id;
    private int demage;
    private int price;
    private String name;

    public Weapon(String name, int id, int demage, int price) {
        this.id = id;
        this.demage = demage;
        this.price = price;
        this.name = name;
    }

    public static Weapon[] weapons(){
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] = new Weapon("Tabanca",1,2,15);
        weaponList[1] = new Weapon("Kılıç",2,3,35);
        weaponList[2] = new Weapon("Tüfek",3,7,45);

        return weaponList;
    }

    public static  Weapon getWeaponObjById(int Id){
        for (Weapon w : Weapon.weapons()){
            if(w.getId() == Id)
                return w;
        }
        return  null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDemage() {
        return demage;
    }

    public void setDemage(int demage) {
        this.demage = demage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
