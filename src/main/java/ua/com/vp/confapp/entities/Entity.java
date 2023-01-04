package ua.com.vp.confapp.entities;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    protected Entity() {
    }
}
