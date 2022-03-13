
import java.util.Random;

public class BattleLocation extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLocation(Player player, String name,Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsCaount = this.randomObstacleCount();
        SlowlyWrite.slowPrint("\t\tŞuannki konumunuz: "+ this.getName(), 5);
        SlowlyWrite.slowPrint("\t\tDikkatli Ol! Burada "+obsCaount+" tane "+this.getObstacle().getName()+ " Yaşıyor !",5, SlowlyWrite.RED_BRIGHT);
        System.out.print("\t\t\t"+SlowlyWrite.BLUE_BOLD+" <S>avaş yada <K>aç    ");
        SlowlyWrite.setResetColor();
        String selectCase = input.nextLine().toUpperCase();

        if(selectCase.equals("S")){
            System.out.print("\t\t\t"+SlowlyWrite.RED_UNDERLINED+"Savaş Başlıyor");
            SlowlyWrite.slowPrint(" ...",200,SlowlyWrite.RED_BRIGHT);
            if(this.combat(obsCaount)){
                System.out.print("\t\t\t\t");
                SlowlyWrite.slowPrint("Tüm Düşmanı Yendiniz", 0, SlowlyWrite.GREEN_BACKGROUND);
                this.setPlayerAward();
                return  true;
            }
        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.print("\t\t\t\t");
            SlowlyWrite.slowPrint("Öldünüz", 0, SlowlyWrite.RED_BACKGROUND);
            return false;
        }
        return true;
    }

    public boolean combat(int obsCount){
        for (int i= 0; i < obsCount; i++){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStatus();
            this.obstacleStatus(i+1);
            int item = randomPlayerOrObstacle(); //ilk önce kimin vuracagını random olarak alır 2 değer alır %50
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.print("\n\t\t\t\t"+SlowlyWrite.BLUE_BRIGHT+"<V>ur yada <K>aç>:  ");
                String selectCombat = input.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    if(item == 1){
                        SlowlyWrite.slowPrint("\n\t\t\t\t\tSiz Vurdunuz",0,SlowlyWrite.GREEN_BRIGHT);
                        obstacle.setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                    if(this.getObstacle().getHealth() > 0){
                        SlowlyWrite.slowPrint("\n\t\t\t\t\tCanavar Size vurdu",0,SlowlyWrite.RED_BRIGHT);
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage < 0)
                            obstacleDamage = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                    item = 1;
                }else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.print("\t\t\t\t");
                SlowlyWrite.slowPrint("Düşmanı Yendiniz", 0, SlowlyWrite.GREEN_BACKGROUND);
                System.out.print("\t\t\t\t");
                SlowlyWrite.slowPrint(this.getObstacle().getAward()+ " para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                SlowlyWrite.slowPrint("\t\t\t\tGüncel paranız: "+this.getPlayer().getMoney(), 0, SlowlyWrite.GREEN_BRIGHT);
            }
        }
        if(!(this.getPlayer().getHealth() <= 0))
            return true;
        return false;
    }

    private void afterHit() {
        SlowlyWrite.slowPrint("\t\t\t\t\tCanınız: "+this.getPlayer().getHealth(),2);
        SlowlyWrite.slowPrint("\t\t\t\t\t"+this.getObstacle().getName()+" Canı: "+this.getObstacle().getHealth(),2);

    }

    public void playerStatus() {
        SlowlyWrite.slowPrint("\n\t\t\t\tOyuncu Değerleri",2, SlowlyWrite.PURPLE_BRIGHT);
        SlowlyWrite.slowPrint("\t\t\t------------------------------------------",15,1.6);
        SlowlyWrite.slowPrint("\t\t\t\t\tSağlık: "+this.getPlayer().getHealth(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tSilah: "+this.getPlayer().getInventory().getWeapon().getName(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tHasar: "+this.getPlayer().getTotalDamage(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tZırh: "+this.getPlayer().getInventory().getArmor().getName(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tBloklama: "+this.getPlayer().getInventory().getArmor().getBlock(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tPara: "+this.getPlayer().getMoney(),5);
    }

    public void obstacleStatus(int i){
        SlowlyWrite.slowPrint("\n\t\t\t\t"+i+". "+this.getObstacle().getName()+" Değerleri",2, SlowlyWrite.PURPLE_BRIGHT);
        SlowlyWrite.slowPrint("\t\t\t------------------------------------------",15,1.6);
        SlowlyWrite.slowPrint("\t\t\t\t\tSağlık: "+this.getObstacle().getHealth(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tHasar: "+this.getObstacle().getDamage(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tÖdül: "+this.getObstacle().getAward(),5);

    }

    public int randomObstacleCount(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public int randomPlayerOrObstacle(){
        Random r = new Random();
        return r.nextInt(2)+1;
    }

    public void setPlayerAward(){
        String message;
        if(this.getAward().equals("food")) {
            this.getPlayer().getInventory().setFood(true);
            message = "Yemek";
        }
        else if(this.getAward().equals("water")) {
            this.getPlayer().getInventory().setWater(true);
            message ="Su";
        }
        else {
            this.getPlayer().getInventory().setFirewoord(true);
            message = "Odun";
        }
        SlowlyWrite.slowPrint("\t\t\t"+message+" ödülünü aldınız bir sonraki bölüme geçebilirsiniz", 2, SlowlyWrite.PURPLE_BRIGHT);

    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
