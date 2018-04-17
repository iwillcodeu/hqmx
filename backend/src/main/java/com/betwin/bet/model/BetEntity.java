package com.betwin.bet.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "bet")
public class BetEntity implements Serializable {

    private static final long serialVersionUID = 949085089040162565L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Type(type = "objectid")
    @Field("_id")
    private String id;

    @Field("account")
    private String account;

    @Field("game_id")
    private String gameId;

    @Field("option")
    private String option;

    @Field("win")
    private boolean win;

    @Field("amount")
    private double amount;

    @Field("bet_time")
    private Date betTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
