package Main.Data;

import Main.Class.Events.Event;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class PerMissionData  implements Serializable {
    public Long owner = 492397708L;

    public ConcurrentHashMap<String, Event> events = new ConcurrentHashMap<>();

    public PerMissionData() {
    }
}
