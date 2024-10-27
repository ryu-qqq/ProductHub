package com.ryuqq.setof.core;

public enum OptionType {

    OPTION_ONE,
    OPTION_TWO,
    SINGLE;

    public boolean isMultiOption(){
        return !this.equals(SINGLE);
    }
}
