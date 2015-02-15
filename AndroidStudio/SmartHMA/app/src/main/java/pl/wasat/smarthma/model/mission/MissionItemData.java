package pl.wasat.smarthma.model.mission;

import java.io.Serializable;

public class MissionItemData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String contentLink;
    private String imgLink;
    private String summary;

    public MissionItemData() {
        // TODO Auto-generated constructor stub
    }

    public MissionItemData(String name) {
        super();
        this.id = 999;
        this.name = name;
        this.contentLink = "http://";
        this.imgLink = "http://";
        this.summary = "summary";
    }

    public MissionItemData(int id, String name, String contentLink,
                           String imgLink, String summary) {
        super();
        this.id = id;
        this.name = name;
        this.contentLink = contentLink;
        this.imgLink = imgLink;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}