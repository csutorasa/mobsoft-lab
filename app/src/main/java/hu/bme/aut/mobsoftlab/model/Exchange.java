package hu.bme.aut.mobsoftlab.model;

import com.orm.dsl.Table;

@Table
public class Exchange {
    private Long id;

    private String from;
    private String to;

    public Exchange() {

    }

    public Exchange(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
