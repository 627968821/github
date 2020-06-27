public class Fighter {
    private String name;
    private int bloodVolume = 1000;//血量
    private Weapon weapon;//武器
    private Skills skills;//技能
    private int dizzQuantity;//眩晕值
    private int gas;//怒气满5释放专属技能后怒气-5
    private int armorValue;//防御力
   // private int

    public int getGas() {
        return gas;
    }

    public int setGas(int gas) {
        return this.gas;
    }

    public Fighter() {
    }

    public int getBloodVolume() {
        return bloodVolume;
    }

    public String getName() {
        return name;
    }

    public Fighter(String name, int bloodVolume, Weapon weapon, Skills skills, int dizzQuantity,int gas) {
        this.name = name;
        this.bloodVolume = bloodVolume;
        this.weapon = weapon;
        this.skills = skills;
        this.dizzQuantity = dizzQuantity;
        this.gas=gas;
    }

    public int getDizzQuantity() {
        return dizzQuantity;
    }

    public void attack(Fighter fighter) {//调用技能攻击共计 fighter
        skills.apply(this, fighter);
    }

    public void hurt(int hurt) {//收到伤害扣除血量
        bloodVolume -= (hurt-armorValue);
        System.out.println(name + "受到伤害" + (hurt-armorValue));
        System.out.println("当前血量"+bloodVolume);
        recover();
        gas++;
    }

    public void vertigo(int rounds) {//受到眩晕伤害，增加眩晕值，眩晕值如果不为零则会跳过一个回合攻击
        bloodVolume -= (rounds-armorValue);
        recover();
        System.out.println(name + "受到伤害" + (rounds-armorValue));
        System.out.println("当前血量"+bloodVolume);
        dizzQuantity++;
        System.out.println(name + "眩晕" + dizzQuantity + "回合");
        gas++;
    }

    public int getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }

    public void recover() {//从眩晕中恢复，眩晕值如果不为零则减一
        if (dizzQuantity == 1) {
            dizzQuantity--;
        }
    }

    public void setBloodVolume(int bloodVolume) {
        this.bloodVolume = bloodVolume;
    }
}



