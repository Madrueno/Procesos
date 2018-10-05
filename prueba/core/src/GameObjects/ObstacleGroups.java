package GameObjects;
import java.util.ArrayList;

public class ObstacleGroups {

    static ArrayList <Obstacle> obstacleActive1; //Un Array para cada barrera
    static ArrayList <Obstacle> obstacleActive2;
    static ArrayList <Obstacle> obstacleActive3;
    static ArrayList <Obstacle> obstacleActive4;

    public ObstacleGroups(float x, float y ){ //Constructor que inicializa las barreras

        //Tiene que haber 4 barreras:
        //Primera barrera:
        this.obstacleActive1= new ArrayList<Obstacle>();
        this.obstacleActive1.add(new Obstacle(x/5, y-23));


        //Segunda barrera:
        this.obstacleActive2= new ArrayList<Obstacle>();


        //Tercera barrera:
        this.obstacleActive3= new ArrayList<Obstacle>();

        //Cuarta barrera:
        this.obstacleActive4= new ArrayList<Obstacle>();

    }

    public ArrayList<Obstacle> getObstacleActive1(){
        return obstacleActive1;
    }
    public ArrayList<Obstacle> getObstacleActive2(){
        return obstacleActive2;
    }
    public ArrayList<Obstacle> getObstacleActive3(){
        return obstacleActive3;
    }
    public ArrayList<Obstacle> getObstacleActive4(){
        return obstacleActive4;
    }
}
