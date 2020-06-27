public class WeaponRealize extends Fighter  implements Weapon {
    private String weaponName;//武器
    private int attackValue;//攻击力
    private String armorName;//防具
    //private int armorValue;//防御力
    @Override
    public String getWeaponName() {
        return weaponName;
    }

    public WeaponRealize(String name, int bloodVolume, Weapon weapon, Skills skills, int dizzQuantity,int gas) {
        super(name, bloodVolume, weapon, skills, dizzQuantity,gas);

    }

    @Override
    public int attack() {
        return attackValue;
    }
    public WeaponRealize(String weaponName,String armorName) {
        this.weaponName = weaponName;
      //attackValue=setAttackValue(weaponName);
       // setAttackValue(weaponName);
        this.armorName=armorName;

    }
    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(String weaponName) {
       // int temp = 0;
        if (weaponName.equals("倚天剑")) {
           attackValue= 15;
        }else if(weaponName.equals("屠龙刀")) {
            attackValue=(int) (Math.random() * (30 - 20 + 1) + 20);
        }else if(weaponName.equals("圣火令")){
            attackValue=50;
        }else if(weaponName.equals("九曲浮沉")){
            attackValue=45;
        }
    }
    public void setArmorValue(String armorName){
        if(armorName.equals(("禁忌铠甲"))){
            super.setArmorValue(20);
        }else if(armorName.equals("神圣袈裟")){
            super.setArmorValue((int) (Math.random() * (30 - 20 + 1) + 10));
           // armorValue=(int) (Math.random() * (30 - 20 + 1) + 10);
        }
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }
}
























