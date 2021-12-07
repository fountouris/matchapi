package com.matchapi.entities;

public enum Sport {
    Football,
    Basketball;


    public static Sport fromInteger(int x) {
        switch(x) {
            case 0:
                return Football;
            case 1:
                return Basketball;
            default:
                return null;
        }
    }

}
