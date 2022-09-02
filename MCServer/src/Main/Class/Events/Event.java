package Main.Class.Events;

import Main.Main;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class Event implements Serializable {
    /**
     * 包含了所有的可以使用的群组
     */
    public ArrayList<Long> enableGroup = new ArrayList<>();
    /**
     * 开启了的群组
     */
    public ArrayList<Long> usingGroups = new ArrayList<>();

    public ArrayList<Long> blackList = new ArrayList<>();


    public Event() {
    }

    public void passEvent(String message, Group group, Contact client) {
        if (verification(group, client)) {
            if (isCommand(message.split(" "))) {
                //判断是否为启用事件
                isAndEnable(message.split(" "), group, client);
                //若返回信息不为空则说明是在这个事件
                String sendMessage = executeCommand(message.split(" "), group, client);
                System.out.println(sendMessage);
                if (sendMessage != null) {
                    if (group != null) {
                        group.sendMessage(sendMessage);
                        return;
                    }
                    client.sendMessage(sendMessage);
                }
            }
        }
    }

    public void isAndEnable(String[] args, Group group, Contact client) {
        if (isCommand(args)) {
            if (args.length == 2) {
                String eventName = args[0].replace("/", "");
                if (eventName.equals(this.getClass().getSimpleName())) {
                    switch (args[1]) {
                        case "enable":
                            enableInGroup(group, client);
                            break;
                        case "disable":
                            disableInGroup(group, client);
                            break;
                        case "addGroup":
                            addGroup(group, client, group.getId());
                            break;
                        case "removeGroup":
                            removeGroup(group, client, group.getId());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public boolean isCommand(String[] args) {
        if (args.length >= 1 && args[0].startsWith("/")) {
            //说明是条指令
            return true;
        }
        return false;
    }


    public abstract String executeCommand(String[] message, Contact group, Contact client);

    public boolean verification(Group group, Contact user) {
        if (String.valueOf(user.getId()).equals(Main.staticData.perMissionData.owner.toString())) {
            return true;
        } else if (blackList.contains(user.getId()) || !enableGroup.contains(group.getId())) {
            return false;
        }
        return true;
    }


    /**
     * 使其能在这个群组内使用
     */
    public void enableInGroup(Group group, Contact contact) {
        if (!enableGroup.contains(group.getId())) {
            group.sendMessage("此群没有使用本事件的权限");
            return;
        }
        //是群主才可以启用这个功能
        if (String.valueOf(group.getOwner().getId()).equals(String.valueOf(contact.getId())) || String.valueOf(contact.getId()).equals(Main.staticData.perMissionData.owner.toString())) {
            if (!usingGroups.contains(group.getId())) {
                usingGroups.add(group.getId());
                group.sendMessage("已开启" + this.getClass().getSimpleName() + "功能");
            } else {
                group.sendMessage("本群已经开启" + this.getClass().getSimpleName() + "功能");
            }
        } else {
            group.sendMessage("你没有权限");
            //反馈无权限信息
        }
    }

    /**
     * 使其不能在这个群组内使用
     */
    public void disableInGroup(Group group, Contact contact) {
        if (!enableGroup.contains(group.getId())) {
            //返回此群没有这个权限消息
            group.sendMessage("此群没有使用本事件的权限");
            return;
        }
        //是群主才可以关闭这个功能
        if (String.valueOf(group.getOwner().getId()).equals(String.valueOf(contact.getId())) || String.valueOf(contact.getId()).equals(Main.staticData.perMissionData.owner.toString())) {
            if (usingGroups.contains(group.getId())) {
                usingGroups.remove(group.getId());
                group.sendMessage("已关闭" + this.getClass().getSimpleName() + "功能");
            } else {
                group.sendMessage("本群没有开启" + this.getClass().getSimpleName() + "功能");
            }

        } else {
            //反馈无权限信息
            group.sendMessage("你没有权限开启这个功能");
        }
    }

    /**
     * 给于使用权限
     */
    public void addGroup(Group group, Contact client, Long groupId) {
        if (Main.staticData.perMissionData.owner.toString().equals(String.valueOf(client.getId()))) {
            enableGroup.add(groupId);
            group.sendMessage("成功为" + groupId + "添加了" + this.getClass().getSimpleName() + "功能");
        }
    }

    /**
     * 移除使用权限
     */
    public void removeGroup(Group group, Contact client, Long groupId) {
        if (Main.staticData.perMissionData.owner.toString().equals(String.valueOf(client.getId()))) {
            enableGroup.remove(groupId);
            group.sendMessage("成功移除了" + groupId + "的" + this.getClass().getSimpleName() + "功能");
        }
    }

}
