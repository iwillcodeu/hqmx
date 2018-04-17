package com.betwin.game.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.betwin.common.BetWinConstants;

public class GameDto implements Serializable {

    private static final long serialVersionUID = -4889581682929530841L;

    private String id;

    @Size(min = 5, max = 30, message = "gameName" + BetWinConstants.MSG_LENGTH_BETWEEN_5_30)
    @Pattern(regexp = "^[a-zA-Z0-9_-]?$")
    private String gameName;

    @Size(min = 5, max = 30, message = "game creator" + BetWinConstants.MSG_LENGTH_BETWEEN_5_30)
    @Pattern(regexp = "^[a-zA-Z0-9_-]?$")
    private String createBy;

    private Date startTime;

    private Date endTime;

    private String progress;

    private String result;

    private List<String> options = new ArrayList<>();

    private String contractAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
