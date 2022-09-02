package Main.Data;

import Main.Class.Entity.category;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StaticData implements Serializable {

    public List<category> categories = new ArrayList<>();

    public Bot bot = BotFactory.INSTANCE.newBot(501864196, "tank:1998", new BotConfiguration() {{
        fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
        setProtocol(MiraiProtocol.ANDROID_WATCH); // 切换协议
    }});
    public ConcurrentHashMap<Long, Bot> bots = new ConcurrentHashMap<>();

    public PerMissionData perMissionData = new PerMissionData();

}
