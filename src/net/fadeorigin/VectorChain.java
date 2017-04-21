package net.fadeorigin;

import java.util.ArrayList;

/**
 * Powered by FadeOrigin
 */
public class VectorChain {
    private ArrayList<Point> pointCollection=new ArrayList<Point>();

    public VectorChain(){

    }

    public VectorChain(VectorChain vectorChain){
        for(Point pointInstance:vectorChain.pointCollection){
            this.addPoint(pointInstance);
        }
    }

    public void addPoint(Point point){
        this.pointCollection.add(point);
    }

    public int getSize(){
        return this.pointCollection.size();
    }

    public Point getStartPoint(){
        if(this.pointCollection.size()>0){
            return this.pointCollection.get(0);
        }else{
            return null;
        }

    }

    public Point getEndPoint(){
        if(this.pointCollection.size()>0){
            return this.pointCollection.get(this.pointCollection.size()-1);
        }else{
            return null;
        }
    }

    public String convertToString(){
        String returnString="";
        for(int forInt_1=0;forInt_1<this.pointCollection.size();forInt_1++){
            returnString+=this.pointCollection.get(forInt_1).convertToString();
            if(forInt_1!=pointCollection.size()-1){
                returnString+="-";
            }
        }
        return returnString;
    }

}
