package Main.Class.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class item {
    public String uuid;
    public String name;
    public HashMap<String, Object> values;


    public item() {
        this.uuid = UUID.randomUUID().toString();
        this.name = "";
        this.values = new HashMap<>();
    }
}
