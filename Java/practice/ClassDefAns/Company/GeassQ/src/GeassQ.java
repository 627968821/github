//#### 7.多人猜拳游戏
//        1. 用户输入多少人参与猜拳（2~5人）
//        2. 计算机控制除1个玩家以外的其他人出拳
//        3. 每次出拳比较后，输的人被淘汰，剩余玩家继续出拳直到一人胜出。
//        4. 每次比较后，输出每个玩家（编号）的出拳和胜负情况
public class GeassQ {
    public interface choice {
        public static final int 石头=1;
        public static final int 剪刀=2;
        public static final int 布=3;
    }

    public enum choose {
        石头,
        剪刀,
        布;
    }


}
