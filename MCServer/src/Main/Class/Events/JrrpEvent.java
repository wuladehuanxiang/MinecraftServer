package Main.Class.Events;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class JrrpEvent extends Event {


    public static ConcurrentHashMap<Long, Integer> values=new ConcurrentHashMap<>();

    public static Random random = new Random();

    public JrrpEvent() {
    }

    @Override
    public String executeCommand(String[] message, Contact group, Contact client) {
        if (message[0].equals("/jrrp")) {
            if (!values.containsKey(client.getId())) {
                int jrrp = random.nextInt(101);
                values.put(client.getId(), jrrp);
                //说明执行的是这个事件
                return ((Group) group).get(client.getId()).getNick() + "人品为" + jrrp;
            }
            return ((Group) group).get(client.getId()).getNick() + "人品为" + values.get(client.getId());
        }
        return null;
    }

}
