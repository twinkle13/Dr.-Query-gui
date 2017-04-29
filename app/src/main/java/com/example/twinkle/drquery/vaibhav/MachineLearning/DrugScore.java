package com.example.twinkle.drquery.vaibhav.MachineLearning;

import java.math.BigDecimal;

public class DrugScore{
    private String drugName;
    private BigDecimal score;


    DrugScore(String drugName, BigDecimal score) {
        this.drugName = drugName;
        this.score = score;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
