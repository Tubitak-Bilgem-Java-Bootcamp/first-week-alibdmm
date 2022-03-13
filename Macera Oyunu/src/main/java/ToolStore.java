public class ToolStore extends  NormalLocation{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation(){
        SlowlyWrite.slowPrint("<-------------------> Mağazaye hoşgeldiniz <------------------->", 5);
        boolean showMenu = true;
        while (showMenu){
            SlowlyWrite.slowPrint("\t\t1 -> Silahlar", 2);
            SlowlyWrite.slowPrint("\t\t2 -> Zırlar", 2);
            SlowlyWrite.slowPrint("\t\t3 -> Çıkış Yap", 2);
            System.out.print("\t\t "+SlowlyWrite.CYAN+"Seçiminiz: ");
            int selectCase = input.nextInt();
            while(selectCase < 1 || selectCase > 3){
                System.out.print("\t "+SlowlyWrite.RED+"Geçersiz değer tekrar seçim yapınız: ");
                selectCase = input.nextInt();
            }
            switch (selectCase){
                case 1:
                    this.printWeapon();
                    this.buyWeapon();
                    break;
                case 2:
                    this.printArmor();
                    this.buyArmor();
                    break;
                case 3:
                    SlowlyWrite.slowPrint("\tÇıkış Yapılıyor",15);
                    showMenu = false;
                    break;
            }
        }
        return super.onLocation();
    }

    public void printWeapon(){
        SlowlyWrite.slowPrint("\t\t\tSilahlar", 8);
        SlowlyWrite.slowPrint("\t\t\t\t0 --> Çıkış Yapınız", 5, SlowlyWrite.RED);
        for( Weapon w : Weapon.weapons()){
            SlowlyWrite.slowPrint("\t\t\t\t"+w.getId()+"\t"+w.getName()+ "\t<Para: " + w.getPrice() + "\t|| \tHasar: " + w.getDemage()+">",5, SlowlyWrite.PURPLE);
        }
    }

    public void  buyWeapon(){
        SlowlyWrite.slowPrint("\t\t\t\tMevcut  silahınız: "+this.getPlayer().getInventory().getWeapon().getName(), 5, SlowlyWrite.BLUE);
        System.out.print("\t\t\t\t\t "+SlowlyWrite.CYAN+"Bir Silah Seçiniz: ");
        SlowlyWrite.setResetColor();
        int selectWeaponId = input.nextInt();
        while(selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length){
            System.out.print("\t\t\t\t "+SlowlyWrite.RED+"Geçersiz değer, tekrar deneyiniz: ");
            selectWeaponId = input.nextInt();
        }
        if(selectWeaponId != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);
            if(selectedWeapon !=null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    SlowlyWrite.slowPrint("\t\tYeterli Paranız bulnmamaktadır !", 5, SlowlyWrite.RED);
                }else{
                    System.out.print("\t\t");
                    SlowlyWrite.slowPrint(selectedWeapon.getName() + " satın aldınız", 5, SlowlyWrite.GREEN_BACKGROUND);
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    SlowlyWrite.slowPrint("\t\tKalan paranız --> "+ this.getPlayer().getMoney(), 5);

                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void printArmor(){
        SlowlyWrite.slowPrint("\t\t\tZırhlar", 8);
        SlowlyWrite.slowPrint("\t\t\t\t0 --> Çıkış Yapınız", 5, SlowlyWrite.RED);
        for( Armor a : Armor.armors()){
            SlowlyWrite.slowPrint("\t\t\t\t"+a.getId()+"\t"+a.getName()+ "\t<Para: " + a.getPrice() + "\t|| \tZırh: " + a.getBlock()+">",5, SlowlyWrite.PURPLE);
        }
    }

    public void buyArmor(){
        SlowlyWrite.slowPrint("\t\t\t\tMevcut  Zırhlarınız: "+this.getPlayer().getInventory().getArmor().getName(), 5, SlowlyWrite.BLUE);
        System.out.print("\t\t\t\t\t "+SlowlyWrite.CYAN+"Bir Zırh Seçiniz: ");
        SlowlyWrite.setResetColor();
        int selectArmorId = input.nextInt();
        while(selectArmorId < 0 || selectArmorId > Armor.armors().length){
            System.out.print("\t\t\t\t "+SlowlyWrite.RED+"Geçersiz değer, tekrar deneyiniz: ");
            selectArmorId = input.nextInt();
        }

        if(selectArmorId != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);
            if(selectedArmor !=null){
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    SlowlyWrite.slowPrint("\t\tYeterli Paranız bulnmamaktadır !", 5, SlowlyWrite.RED);
                }else{
                    System.out.print("\t\t");
                    SlowlyWrite.slowPrint(selectedArmor.getName() + " Satın aldınız", 5, SlowlyWrite.GREEN_BACKGROUND);
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    SlowlyWrite.slowPrint("\t\tKalan paranız --> "+ this.getPlayer().getMoney(), 5);

                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }
    }
}
