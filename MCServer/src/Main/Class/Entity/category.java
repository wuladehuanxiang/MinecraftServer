package Main.Class.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class category {
    public String uuid;
    public String name;
    public List<type> types;

    public category() {
        this.uuid = UUID.randomUUID().toString();
        this.name = "";
        this.types = new ArrayList<>();
    }
}
