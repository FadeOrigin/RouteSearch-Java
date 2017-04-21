package net.fadeorigin;

import java.util.ArrayList;

/**
 * Powered by FadeOrigin
 */
public class Point {
    private String name;
    private String alias;

    public Point(String name){
        this.name=name;
        this.alias="";
    }

    public Point(String name,String alias){
        this.name=name;
        this.alias=alias;
    }

    public String getName(){
        return this.name;
    }

    public String getAlias(){
        return this.alias;
    }

    public String convertToString(){
        return this.alias;
    }

    //
    //one step forward
    public ArrayList<Point> march(){
        ArrayList<Route> routeCollection=RouteDataManager.getInstance().getRouteCollectionThroughPoint(this);
        ArrayList<Point> marchPointCollection=new ArrayList<Point>();
        for(Route routeInstance:routeCollection){
            marchPointCollection.add(routeInstance.getAnotherPoint(this));
        }
        return marchPointCollection;
    }
}
