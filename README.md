# RouteSearch- .Java
**Search for shortest route based on point and route data.**

**Sample Code:**  
RouteDataManager routeDataManager=RouteDataManager.getInstance();  
//  
//there are 5 points  
Point pointA=new Point("pointA");  
Point pointB=new Point("pointB");  
Point pointC=new Point("pointC");  
Point pointD=new Point("pointD");  
Point pointE=new Point("pointE");  
//  
//add route data  
routeDataManager.addRoute(new Route(pointA,pointB));  
routeDataManager.addRoute(new Route(pointB,pointC));  
routeDataManager.addRoute(new Route(pointC,pointE));  
routeDataManager.addRoute(new Route(pointA,pointD));  
routeDataManager.addRoute(new Route(pointD,pointE));  
//  
//add reachable point data, notice that pointD is unreachable now  
ArrayList<Point> transitablePointCollection=new ArrayList<Point>();  
transitablePointCollection.add(pointA);  
transitablePointCollection.add(pointB);  
transitablePointCollection.add(pointC);  
transitablePointCollection.add(pointE);  
//  
//the shortest route from A to E is A-D-E, but D is unreachable, so algorithm return A-B-C-E instead  
Explore explore=new Explore(transitablePointCollection,pointA,pointE);  
System.out.println(explore.getMinDistance());  

**Other version**  
.net Core:https://github.com/FadeOrigin/RouteSearch-.NetCore
