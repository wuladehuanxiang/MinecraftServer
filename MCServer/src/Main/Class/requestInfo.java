package Main.Class;

public class requestInfo {

    public requestType requestType;

    public String jsonString;

    public String className;

    enum requestType {
        request_info, update, delete, create
    }
}
