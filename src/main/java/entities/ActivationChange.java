package entities;

import java.sql.Timestamp;

public class ActivationChange {
    //todo add new activation change when adding new customer to table
    private Integer id;
    private Boolean active;
    private Timestamp datetime;
    private Integer customerId;

    public ActivationChange(Integer id, Boolean active, Timestamp datetime, Integer customerId) {
        this.id = id;
        this.active = active;
        this.datetime = datetime;
        this.customerId = customerId;
    }

//    public Integer getId() {
//
//    }

    public Boolean getActive() {
        return active;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

}
