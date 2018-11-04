package DF;

public interface GroupBy {

    default DataFrame max() { return apply(new ApplyMax());}
    default DataFrame min() { return apply(new ApplyMin());}
    default DataFrame mean() { return apply(new ApplyMean());}
    default DataFrame std() { return apply(new ApplyStd());}
    default DataFrame sum() { return apply(new ApplySum());}
    default DataFrame var() { return apply(new ApplyVar());}

    DataFrame apply(Applyable funkcja);
}
