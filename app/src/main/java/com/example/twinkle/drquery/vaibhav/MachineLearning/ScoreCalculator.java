package com.example.twinkle.drquery.vaibhav.MachineLearning;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by root on 29/4/17.
 */



public class ScoreCalculator {
    private HashMap<String, LinkedList<BigDecimal>> seLikelihood;
    private HashMap<String, LinkedList<BigDecimal>> iLikelihood;
    private HashMap<String, LinkedList<BigDecimal>> ddLikelihood;
    private  LinkedList<String> drugs;
    Context context;

    public ScoreCalculator(Context context) throws IOException, ClassNotFoundException {
        this.context = context;
        readLikelihood();
    }

    private void readLikelihood() throws IOException, ClassNotFoundException {
        InputStream is = null;
        ObjectInputStream oin = null;

        //read drugs in graph
        is = context.getResources().getAssets().open("drugs.txt");
        oin = new ObjectInputStream(is);
        drugs = (LinkedList<String>)oin.readObject();

        //read SideEffect Likelihood
        is = context.getResources().getAssets().open("sideeffect.txt");
        oin = new ObjectInputStream(is);
        seLikelihood = (HashMap<String, LinkedList<BigDecimal>>)oin.readObject();

        is = context.getResources().getAssets().open("indication.txt");
        oin = new ObjectInputStream(is);
        iLikelihood = (HashMap<String, LinkedList<BigDecimal>>)oin.readObject();

        is = context.getResources().getAssets().open("druginteraction.txt");
        oin = new ObjectInputStream(is);

        ddLikelihood = (HashMap<String, LinkedList<BigDecimal>>)oin.readObject();
    }

    private BigDecimal getLikelihood(String keyword, String type, String drug){
        if(keyword.equals(""))
            return null;
        int index = drugs.indexOf(drug);
        if(type.equals("SideEffect")) {
            return seLikelihood.get(keyword).get(index);
        }
        else if(type.equals("Indication")){
            return iLikelihood.get(keyword).get(index);
        }
        else if(type.equals("DrugInteraction")){
            return ddLikelihood.get(keyword).get(index);
        }
        else{
            Toast.makeText(context, "Please enter a valid type", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private BigDecimal getScore(String seKey, String iKey, String ddKey, String drug){
        BigDecimal unit = new BigDecimal("1");
        BigDecimal score = new BigDecimal("1");
        BigDecimal seScore = getLikelihood(seKey, "SideEffect", drug);
        BigDecimal iScore = getLikelihood(iKey, "Indication", drug);
        BigDecimal ddScore = getLikelihood(ddKey, "DrugInteraction", drug);
        if(seScore != null)
            seScore = unit.subtract(seScore);
        else
            seScore = new BigDecimal("1");
        if(ddScore != null)
            ddScore = unit.subtract(ddScore);
        else
            ddScore = new BigDecimal("1");
        if(iScore == null)
            iScore = new BigDecimal("1");

        score = seScore.multiply(ddScore);
        return score.multiply(iScore);

    }

    public PriorityQueue<DrugScore> getTopScore(String seKey, String iKey, String ddKey){
        DrugScore drugScore = null;
        PriorityQueue<DrugScore> heap = new PriorityQueue<>(10,new Comparator<DrugScore>(){
            @Override
            public int compare(DrugScore t1, DrugScore t2) {
                return t1.getScore().compareTo(t2.getScore());
            }
        });
        for(String drug:drugs){
            heap.add(new DrugScore(drug, getScore(seKey, iKey, ddKey, drug)));
        }
        return heap;
    }

}

