package pl.wasat.smarthma.parser.model;

/**
 * Created by marcel on 2015-08-04 00:09.
 * Part of the project  SmartHMA
 */
public class Page {
    private int id;
    private int category_id;
    private int mission_id;
    private String name;
    private String data;

    public Page(int id, int category_id, int mission_id, String name, String data) {
        this.id = id;
        this.category_id = category_id;
        this.mission_id = mission_id;
        this.name = name;
        this.data = data;
    }


    public Page(int category_id, int mission_id, String name, String data) {
        this.category_id = category_id;
        this.mission_id = mission_id;
        this.name = name;
        this.data = data;
    }

    public Page() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", mission_id=" + mission_id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

}
