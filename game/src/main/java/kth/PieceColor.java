package kth;

import java.io.Serializable;

public enum PieceColor implements Serializable {
    None, Red, Black;

    public String localName() {
        if(this == Red) return "Röd";
        else if(this == Black) return "Svart";
        else return "Ingen";
    }
}
