package Main.Class.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class type {
    public String uuid;
    public String name;
    public List<item> items;

    public type() {
        this.uuid = UUID.randomUUID().toString();
        this.name = "";
        this.items = new ArrayList<>();
    }
}
