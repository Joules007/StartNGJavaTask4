package ng.com.ajsprojects.startngjavatask4;

import java.io.Serializable;

public class DataModel implements Serializable {
    private String id;
    private String title;
    private String body;

    public DataModel(){}

    public DataModel(String title, String body) {
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
