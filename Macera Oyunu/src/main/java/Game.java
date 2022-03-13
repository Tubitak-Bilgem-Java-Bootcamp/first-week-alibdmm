import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Hoş Geldiniz");
        System.out.println("Lütfe Bir İsim Giriniz: ");
        //String palayerName = input.nextLine();

        Player player =new Player("AAli");
        SlowlyWrite.slowPrint("Sayın "+player.getName() + " bu karanlık cehenneme hoşgeldiniz", 10);
        SlowlyWrite.slowPrint("Burada yaşananlar gerçektir, çok dikkatli olmanız gerekecektir !",10 );
        player.selectChar();

        Location location = null;
        //SlowlyWrite.slowPrint("\n\nBölgeler Getiriliyor ...", 5);

        while (true){
            if(player.getInventory().isWater() && player.getInventory().isFood() && player.getInventory().isFirewoord()){
                SlowlyWrite.slowPrint("\t\t"+SlowlyWrite.RED_BRIGHT+"Tüm ödülleri topladınız güvenli eve dönüp oyunu bitirebilirsiniz!", 2);
                location = new SafeHouse(player);
            }
            SlowlyWrite.slowPrint(player.playerInfo(), 5);
            SlowlyWrite.slowPrint("\n------------------ Bölgeler ------------------", 5);
            SlowlyWrite.slowPrint("1 - Güvenli Ev --> Burası sizin için güveli bir alandır, düşman yoktur !", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("2 - Mağaza --> Silav ve zırh satın alabilirsiniz !", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("3 - Mağara --> Canavar ile savaş yemeği kazan", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("4 - Orman --> Vampir ile savaş odunları topla", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("5 - Nehir --> Ayı ile savaş suyu kazan", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("0 - Çıkış Yapınız !", 5, SlowlyWrite.RED);

            System.out.print("\n"+SlowlyWrite.GREEN+"Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            SlowlyWrite.setResetColor();
            int selectLoc = input.nextInt();
            boolean flag = true;
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInventory().isFood()) {
                        SlowlyWrite.slowPrint("\t\tBu bölümdeki tüm ödülleri aldınız lütfen başka bir bölüm seçiniz!", 2, SlowlyWrite.WHITE_BRIGHT);
                        flag = false;
                        break;
                    }
                    location = new Cave(player);
                    break;
                case 4:
                    if(player.getInventory().isFirewoord()){
                        SlowlyWrite.slowPrint("\t\tBu bölümdeki tüm ödülleri aldınız lütfen başka bir bölüm seçiniz!", 2, SlowlyWrite.WHITE_BRIGHT);
                        flag = false;
                    }
                    location = new Forest(player);
                    break;
                case 5:
                    if(player.getInventory().isWater()){
                        SlowlyWrite.slowPrint("\t\tBu bölümdeki tüm ödülleri aldınız lütfen başka bir bölüm seçiniz!", 2, SlowlyWrite.WHITE_BRIGHT);
                        flag = false;
                    }
                    location = new River(player);
                    break;
                default:
                    location = new SafeHouse(player);
            }
            if(location == null){
                SlowlyWrite.slowPrint("GAME OVER !", 5, SlowlyWrite.RED_BACKGROUND);
                break;
            }
            if (flag && !location.onLocation()){
                SlowlyWrite.slowPrint("GAME OVER !", 5, SlowlyWrite.RED_BACKGROUND);
                break;
            }
        }
    }
}
