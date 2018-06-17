package com.betwin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Document(collection = "HotGame")
public class HotGame implements Serializable {

    @Id
    private String id;
    @Indexed
    private Date gameDate;
    private String teams;
    @Indexed
    private String match;
}
