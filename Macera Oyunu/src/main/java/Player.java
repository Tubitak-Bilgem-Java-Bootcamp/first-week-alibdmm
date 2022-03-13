import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Player {
    private int damage;
    private int health;
    private int money;
    private String name;
    private String charName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;
    private int originalHealt;

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        GameChar charList[] = {new Samurai(),  new Archer(), new Knight()};
        SlowlyWrite.slowPrint("Karakterler Yükleniyor ...", 5);
        SlowlyWrite.slowPrint("\n##################################################################################\n",20, SlowlyWrite.RED);
        for(GameChar gameChar : charList){
            SlowlyWrite.slowPrint("Id: "+gameChar.getId()+
                    "\t\t Karakter: "+gameChar.getName()+" " +
                    "\t\t Hasar: "+gameChar.getDamage()+" " +
                    "\t\t Sağlık: "+gameChar.getHealth()+" " +
                    "\t\t Para: "+gameChar.getMoney(), 20, 2, SlowlyWrite.PURPLE);
        }
        SlowlyWrite.slowPrint("\n##################################################################################",10, SlowlyWrite.RED);
        System.out.print("\n"+SlowlyWrite.GREEN+"Lütfen bir karakter giriniz: ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPalyer(new Samurai());
                break;
            case 2:
                initPalyer(new Archer());
                break;
            case 3:
                initPalyer(new Knight());
                break;
            default:
                initPalyer(new Samurai());
        }

    }

    public void initPalyer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setOriginalHealt(gameChar.getHealth());
        //SlowlyWrite.slowPrint(gameChar.toString(),15);
    }

    public String playerInfo(){
        return "\t\tSihanız: " + this.getInventory().getWeapon().getName() +
                "\t||\tZırh: " + this.getInventory().getArmor().getName()+
                "\t||\tEngelleme: " + this.getInventory().getArmor().getBlock()+
                "\t||\tHasarınız: " + this.getTotalDamage() +
                "\t||\tSağlık: " + this.getHealth() +
                "\t||\tPara: " + this.getMoney()+
                "\t||\tYemek: " + (this.getInventory().isFood() ? "Var" : "Yok")+
                "\t||\tSu: "+ (this.getInventory().isWater() ? "Var" : "Yok")+
                "\t||\tOdun: "+(this.getInventory().isFirewoord() ? "Var" : "Yok");

    }

    public int getTotalDamage(){
        return this.damage  + this.inventory.getWeapon().getDemage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage(){
        return this.damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealt() {
        return originalHealt;
    }

    public void setOriginalHealt(int originalHealt) {
        this.originalHealt = originalHealt;
    }
}
