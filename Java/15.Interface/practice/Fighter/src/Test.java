public class Test {
   // private static Object SkillsRealize;

    public static void main(String[] args) {
        Fighter fighter1 = new WeaponRealize("张无忌", 1000, new WeaponRealize("圣火令","禁忌铠甲"), new SkillsRealize(), 0,0);
       ((WeaponRealize)fighter1).setAttackValue("圣火令");
       ((WeaponRealize)fighter1).setArmorValue("禁忌铠甲");

        Fighter fighter2 = new WeaponRealize("灭绝师太", 1000, new WeaponRealize("九曲浮沉","神圣袈裟"), new SkillsRealize(), 0,0);
      // ((WeaponRealize)fighter2).setAttackValue("倚天剑");
       ((WeaponRealize)fighter2).setAttackValue("九曲浮沉");
       ((WeaponRealize)fighter2).setArmorValue("神圣袈裟");


        fighter1.attack(fighter2);

    }
}
