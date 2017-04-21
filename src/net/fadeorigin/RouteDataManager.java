package net.fadeorigin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Powered by FadeOrigin
 */
public class RouteDataManager {

    private static RouteDataManager routeDataManager;
    private static ArrayList<Route> routeCollection=new ArrayList<Route>();
    private static ArrayList<Point> pointCollection=new ArrayList<Point>();
    private static ConcurrentHashMap<Point,ArrayList<Route>> routeCollectionGroupByPoint =new ConcurrentHashMap<Point,ArrayList<Route>>();


    public static RouteDataManager getInstance(){
        if(RouteDataManager.routeDataManager ==null){
            RouteDataManager.routeDataManager =new RouteDataManager();
        }
        return RouteDataManager.routeDataManager;
    }

    private RouteDataManager(){

    }


    //
    //put a route into HashMap
    public void addRoute(Route route){
        //
        //check if the route already exists
        if(this.routeExists(route)){
            return;
        }
        //
        //put route into routeCollection
        routeCollection.add(route);
        Point pointA=route.getPointA();
        Point pointB=route.getPointB();
        //
        //put PointA into routeCollectionGroupByPoint
        this.addRouteByRouteAndPoint(route,pointA);
        //
        //put PointB into routeCollectionGroupByPoint
        this.addRouteByRouteAndPoint(route,pointB);
    }

    private void addRouteByRouteAndPoint(Route route, Point point){
        //
        //check if the point is in the route
        if(route.getPointA()!=point && route.getPointB()!=point){
            throw new NullPointerException("point is not in the route");
        }
        //
        //put Point into routeCollectionGroupByPoint
        if(RouteDataManager.routeCollectionGroupByPoint.containsKey(point)){
            ArrayList<Route> pointVectorCollection=RouteDataManager.routeCollectionGroupByPoint.get(point);
            pointVectorCollection.add(route);
        }else{
            ArrayList<Route> pointVectorCollection=new ArrayList<Route>();
            pointVectorCollection.add(route);
            RouteDataManager.routeCollectionGroupByPoint.put(point,pointVectorCollection);
            RouteDataManager.pointCollection.add(point);
        }
    }

    public void printRouteData(){
        Iterator vectorIterator= routeCollectionGroupByPoint.entrySet().iterator();
        while(vectorIterator.hasNext()){
            Map.Entry<Point,ArrayList<Route>> entry=(Map.Entry<Point,ArrayList<Route>>) vectorIterator.next();
            Point point=entry.getKey();
            ArrayList<Route> routeCollection=entry.getValue();
            System.out.print("point="+point.convertToString()+ " route=");
            for(Route routeInstance:routeCollection){
                System.out.print(routeInstance.convertToString(point)+" ");
            }
            System.out.println();
        }
    }

    public boolean routeExists(Route route){
        boolean exists=false;
        for(Route routeInstance:RouteDataManager.routeCollection){
            if(route.isSameRoute(routeInstance)){
                exists=true;
            }
        }
        return exists;
    }

    public ArrayList<Route> getRouteCollectionThroughPoint(Point point){
        return RouteDataManager.routeCollectionGroupByPoint.get(point);
    }

    public ArrayList<Point> getPointCollection(){
        return RouteDataManager.pointCollection;
    }


}
