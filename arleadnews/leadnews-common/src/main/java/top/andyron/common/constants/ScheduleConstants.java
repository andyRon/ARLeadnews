package top.andyron.common.constants;

public class ScheduleConstants {

    /**
     * task状态
     *
     * SCHEDULED 初始化状态
     * EXECUTED 已执行状态
     * CANCELLED 已取消状态
     */
    public static final int SCHEDULED=0;
    public static final int EXECUTED=1;
    public static final int CANCELLED=2;

    /**
     * task缓存key的前缀
     *
     * TOPIC 当前数据key前缀
     * FUTURE 未来数据key前缀
     */
    public static String FUTURE="future_";
    public static String TOPIC="topic_";
}
