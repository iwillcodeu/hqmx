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
    private String uuid;

    @Field("account")
    private String account;

    private String gameId;

    @Field("bet")
    private boolean bet;

    @Field("win")
    private boolean win;

    @Field("selection")
    private String selection;

    @Field("amount")
    private double amount;

    @Field("bet_time")
    private Date betTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public boolean isBet() {
        return bet;
    }

    public void setBet(boolean bet) {
        this.bet = bet;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
