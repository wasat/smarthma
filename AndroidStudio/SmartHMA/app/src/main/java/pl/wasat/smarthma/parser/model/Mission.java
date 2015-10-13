package pl.wasat.smarthma.parser.model;

/**
 * Created by marcel on 2015-08-04 00:09.
 * Part of the project  SmartHMA
 */
public class Mission {
    private int id;
    private int category_id;
    private String name;
    private String data;
    private String imageUrl;

    public Mission(int id, int category_id, String name, String data, String imageUrl) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.data = data;
        this.imageUrl = imageUrl;
    }

    public Mission(int id, int category_id, String name, String data) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.data = data;
    }

    public Mission(int id, int category_id, String name) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
    }

    public Mission() {

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
