package net.fadeorigin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Powered by FadeOrigin
 */
public class Explore {
    private ArrayList<Point> bannedPointCollection=new ArrayList<Point>();
    private ConcurrentHashMap<Point,VectorChain> vectorChainMappedByPoint =new ConcurrentHashMap<Point,VectorChain>();
    VectorChain finalVectorChain =null;

    public Explore(ArrayList<Point> transitablePointCollection,Point startPoint,Point endPoint){
        //
        //any point not in the transitablePointCollection will be add to the bannedPointCollection
        for(Point pointInstance:RouteDataManager.getInstance().getPointCollection()){
            if(transitablePointCollection.contains(pointInstance)==false){
                this.bannedPointCollection.add(pointInstance);
            }
        }
        //
        VectorChain initialVectorChain=new VectorChain();
        initialVectorChain.addPoint(startPoint);
        this.vectorChainMappedByPoint.put(startPoint,initialVectorChain);
        while(this.finalVectorChain ==null){
            ConcurrentHashMap<Point,VectorChain> marchVectorChainMappedByPoint=new ConcurrentHashMap<Point,VectorChain>();
            //
            //update bannedPointCollection
            for(Point nowPointInstance:this.vectorChainMappedByPoint.keySet()){
                this.bannedPointCollection.add(nowPointInstance);
            }
            //
            //one step forward
            boolean allDeadWay=true;
            for(Point nowPointInstance:this.vectorChainMappedByPoint.keySet()){
                for(Point targetPointInstance:nowPointInstance.march()){
                    if(this.bannedPointCollection.contains(targetPointInstance)){
                        //
                        //deadWay
                    }else{
                        allDeadWay=false;
                        if(marchVectorChainMappedByPoint.containsKey(targetPointInstance)){
                            //
                            //nothing to do
                        }else{
                            VectorChain vectorChainBeforeMarch=this.vectorChainMappedByPoint.get(nowPointInstance);
                            VectorChain vectorChainAfterMarch=new VectorChain(vectorChainBeforeMarch);
                            vectorChainAfterMarch.addPoint(targetPointInstance);
                            marchVectorChainMappedByPoint.put(targetPointInstance,vectorChainAfterMarch);
                            if(targetPointInstance==endPoint){
                                this.finalVectorChain=vectorChainAfterMarch;
                                break;
                            }
                        }
                    }
                }
            }
            vectorChainMappedByPoint=marchVectorChainMappedByPoint;

            if(allDeadWay==true){
                break;
            }
            //
        }
    }

    public void printBannedPointCollection(){
        System.out.print("bannedPointCollection：");
        for(Point pointInstance:this.bannedPointCollection){
            System.out.print(pointInstance.convertToString()+" ");
        }
        System.out.println();
    }


    public void printFinalVectorChain(){
        if(this.finalVectorChain!=null){
            System.out.println(this.finalVectorChain.convertToString());
        }else{
            System.out.println("unreachable");
        }
    }

    /**
     * get the minimum distance
     */
    public int getMinDistance(){
        if(this.finalVectorChain !=null){
            return this.finalVectorChain.getSize()-1;
        }else{
            return -1;
        }
    }

    public void printVectorChainMappedByPoint(){
        System.out.print("vectorChainMappedByPoint：");
        Iterator vectorChainIterator= this.vectorChainMappedByPoint.entrySet().iterator();
        while(vectorChainIterator.hasNext()){
            Map.Entry<Point,VectorChain> entry=(Map.Entry<Point,VectorChain>) vectorChainIterator.next();
            Point point=entry.getKey();
            VectorChain vectorChain=entry.getValue();
            System.out.print("point="+point.convertToString()+ " ");
            System.out.print("vectorChain="+vectorChain.convertToString());
            System.out.print(" ");
        }
        System.out.println();
    }

}
