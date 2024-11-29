package com.ryuqq.setof.enums.core;

public enum OptionType {

    OPTION_ONE,
    OPTION_TWO,
    SINGLE;

    public boolean isMultiOption(){
        return !this.equals(SINGLE);
    }


}
