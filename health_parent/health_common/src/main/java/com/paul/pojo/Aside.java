package com.paul.pojo;

import java.io.Serializable;
import java.util.List;

public class Aside implements Serializable {
    /*
      "path": "1",
      "title": "工作台",
      "icon":"fa-dashboard",
      "children": []
                    */
    
    private String path;
    private String title;
    private String icon;
    private List<Children> children;

    public Aside() {
    }

    public Aside(String path, String title, String icon) {
        this.path = path;
        this.title = title;
        this.icon = icon;
    }
    public Aside(String path, String title, String icon, List<Children> children) {
        this.path = path;
        this.title = title;
        this.icon = icon;
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }
}
