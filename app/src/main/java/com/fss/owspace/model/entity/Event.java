package com.fss.owspace.model.entity;

/**
 * author: .fss
 * date:   2021/5/24 11:42
 * desc:
 */
public class Event {
    Long id;
    String name;
    public Event(long id,String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
