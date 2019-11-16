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

    private Integer path;
    private String title;
    private String icon;
    private List<Children> children;


    public Aside(Integer path, String title, String icon, List<Children> children) {
        this.path = path;
        this.title = title;
        this.icon = icon;
        this.children = children;
    }
}
