public class SkillsRealize extends Fighter implements Skills{
    private boolean zhiRuoAlive=true;


    @Override
    public void apply(Fighter from, Fighter to) {
        if(from.getBloodVolume()>0&&from.getBloodVolume()>0) {
            if (from.getName().equals("张无忌") && from.getGas() >= 5) {
                nineSuns(from, to);
                System.out.println(to.getName() + "受到" + (100 - to.getArmorValue()) + "伤害" + '\n' + "当前血量" + to.getBloodVolume());
                apply(to, from);
            } else if (from.getName().equals("灭绝师太") && from.getGas() >= 5) {
                spiritAttack(from, to);
                System.out.println(to.getName() + "受到" + "100真实伤害" + '\n' + "当前血量" + to.getBloodVolume());
                apply(to, from);
            }
            if ((int) (Math.random() * 2) == 0) {
                doubleAttack(from, to);
                zhiRuoJiuWo(from,to);
                to.hurt(((WeaponRealize) from).attack() * 2);
            } else {
                dizzAttack(from, to);
                if((to.getName().equals("灭绝师太")&to.getBloodVolume()<150)&&zhiRuoAlive){
                    zhiRuoJiuWo(from,to);
                    apply(to,from);
                }else if(to.getName().equals("张无忌")&to.getBloodVolume()<150){
                    System.out.println(to.getName() + "受到眩晕伤害");
                    nineSuns(from,to);
                   to.setBloodVolume (to.getBloodVolume()-(((Weapon) to).attack()-to.getArmorValue())/4);
                    apply(to,from);
                }else
                to.vertigo(((Weapon) from).attack() / 2);
            }
            if (to.getDizzQuantity() == 0) {
                apply(to, from);
            } else
            {apply(from, to);}
        }else{
            if(from.getBloodVolume()<=0){
            System.out.println(to.getName()+"打败了"+from.getName()+'\n');
            System.exit(0);
        }else if(to.getBloodVolume()<=0){
            System.out.println(from.getName()+"打败了"+to.getName()+'\n');
            System.exit(0);

        }
        }
    }
   // 双倍攻击技能
    //使用Fighter from的武器的attack() * 2，作为to 的受到的伤害
    public void doubleAttack(Fighter from,Fighter to){
        System.out.println(from.getName()+"向"+to.getName()+"发起双倍攻击");

    }

//- 眩晕技能
  //  使用Fighter from的武器伤害的 0.5，作为to受到的伤害，to会被眩晕一回合
    public void dizzAttack(Fighter from,Fighter to){
        System.out.println(from.getName()+"向"+to.getName()+"发起眩晕攻击");
    }
    public void nineSuns(Fighter from,Fighter to){//怒气满5自动释放 造成100伤害 张无忌
        System.out.println("怒满 张无忌使用九阳神功,对"+to.getName()+"造成100伤害，防御力减少2");
        to.setBloodVolume(to.getBloodVolume()-(100-to.getArmorValue()));
        to.setArmorValue(to.getArmorValue()-2);
        from.setGas(from.getGas()-5);
        to.setGas(to.getGas()+1);
    }
    public void kunMove(Fighter from,Fighter to){//生命值150以下时被攻击时  张无忌
        if(to.getName().equals("张无忌")&to.getBloodVolume()<150)
        System.out.println("张无忌使用乾坤大挪移,减少一半所受伤害");
        to.setGas(to.getGas()+1);

        // to.setBloodVolume(to.getBloodVolume()-from);

    }
    public void spiritAttack(Fighter from,Fighter to) {//怒气满5自动释放 破防造成50真实伤害 灭绝师太
        System.out.println("怒满 灭绝师太使用精神攻击,对"+to.getName()+"造成100点真实伤害,防御力减少3");
        to.setArmorValue(to.getArmorValue()-3);
        to.setBloodVolume(to.getBloodVolume()-100);
        from.setGas(from.getGas()-5);
        to.setGas(to.getGas()+1);

    }
    public void zhiRuoJiuWo(Fighter from,Fighter to){//生命值150以下触发只能使用一次 免疫所受伤害 控制效果
        //if((to.getName().equals("灭绝师太")&to.getBloodVolume()<150)&&zhiRuoAlive)
            System.out.println("芷若上前挡刀,芷若奄奄一息");
            zhiRuoAlive=false;
            to.setGas(to.getGas()+1);

        }

}


