package net.fadeorigin;

/**
 * Powered by FadeOrigin
 */
public class Route {
    private Point pointA;
    private Point pointB;

    public Route(Point pointA,Point pointB){
        this.pointA=pointA;
        this.pointB=pointB;

    }

    public Point getPointA(){
        return this.pointA;
    }

    public Point getPointB(){
        return this.pointB;
    }

    public String convertToString(){
        return this.getPointA().convertToString()+"-"+this.getPointB().convertToString();
    }

    public String convertToString(Point point){
        Point displayPoint;
        if(this.pointA!=point && this.pointB!=point){
            throw new NullPointerException("point is not in the route");
        }
        if(this.pointA==point){
            displayPoint=this.pointB;
        }else{
            displayPoint=this.pointA;
        }
        return "-"+displayPoint.convertToString();
    }

    public Point getAnotherPoint(Point point){
        if(this.pointA==point){
            return this.pointB;
        }else{
            return this.pointA;
        }
    }

    public boolean isSameRoute(Route anotherRoute){
        if(this.pointA==anotherRoute.pointA && this.pointB==anotherRoute.pointB){
            return true;
        }else if(this.pointA==anotherRoute.pointB && this.pointB==anotherRoute.pointA){
            return true;
        }else{
            return false;
        }
    }
}
