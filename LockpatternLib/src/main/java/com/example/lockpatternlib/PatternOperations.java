package com.example.lockpatternlib;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PatternOperations {
    private ArrayList<Dot> dotList;
    private Context context;
    private MSP storage;

    public PatternOperations(Context context){
        this.context=context;
        storage=MSP.get();
    }
    public void saveDots( ArrayList<DotData> dotList){
        storage.putArray("KEY_DOTS", dotList);

    }
    public ArrayList<DotData> loadDots(){
        TypeToken<ArrayList<DotData>> collectionType = new TypeToken<ArrayList<DotData>>(){};
        return storage.getArray("KEY_DOTS",collectionType );
    }
    
    public boolean checkPatternPressCountMatch(ArrayList<DotData> userInputArray,ArrayList<DotData> storedArray ){
        if(userInputArray.size()!=storedArray.size())
            return false;
        else{
            for (int i=0; i<userInputArray.size(); i++) {
                if (!userInputArray.get(i).comparePressedCount(storedArray.get(i)))
                    return false;
            }
        }
        return true;
    }
    public boolean checkPatternColorMatch(ArrayList<DotData> userInputArray,ArrayList<DotData> storedArray ){
        if(userInputArray.size()!=storedArray.size())
            return false;
        else{
            for (int i=0; i<userInputArray.size(); i++) {
                if (!(userInputArray.get(i).compareDotColor(storedArray.get(i))))
                    return false;
            }
        }
        return true;
    }



}
