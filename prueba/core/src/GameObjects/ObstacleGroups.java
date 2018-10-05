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
            //Parte de abajo
        this.obstacleActive1.add(new Obstacle(x/8, y-(6+50))); //Restamos 6 en y por el ancho
        this.obstacleActive1.add(new Obstacle(x/8+5, y-(6+50))); //Sumamos 5 en x por cada barrera por su ancho
        this.obstacleActive1.add(new Obstacle(x/8+10, y-(6+50)));
            //Parte arriba
        this.obstacleActive1.add(new Obstacle(x/8, y-(6+50+6)));
        this.obstacleActive1.add(new Obstacle(x/8+5, y-(6+50+6)));
        this.obstacleActive1.add(new Obstacle(x/8+10, y-(6+50+6)));

        //Segunda barrera:
        this.obstacleActive2= new ArrayList<Obstacle>();
            //Parte de abajo
        this.obstacleActive2.add(new Obstacle(x/2-20, y-(6+50)));
        this.obstacleActive2.add(new Obstacle(x/2-20+5, y-(6+50)));
        this.obstacleActive2.add(new Obstacle(x/2-20+10, y-(6+50)));
            //Parte de arriba
        this.obstacleActive2.add(new Obstacle(x/2-20, y-(6+50+6)));
        this.obstacleActive2.add(new Obstacle(x/2-20+5, y-(6+50+6)));
        this.obstacleActive2.add(new Obstacle(x/2-20+10, y-(6+50+6)));

        //Tercera barrera:
        this.obstacleActive3= new ArrayList<Obstacle>();
            //Parte de abajo:
        this.obstacleActive3.add(new Obstacle(x/2+20, y-(6+50)));
        this.obstacleActive3.add(new Obstacle(x/2+20-5, y-(6+50)));
        this.obstacleActive3.add(new Obstacle(x/2+20-10, y-(6+50)));
            //Parte de arriba
        this.obstacleActive3.add(new Obstacle(x/2+20, y-(6+50+6)));
        this.obstacleActive3.add(new Obstacle(x/2+20-5, y-(6+50+6)));
        this.obstacleActive3.add(new Obstacle(x/2+20-10, y-(6+50+6)));

        //Cuarta barrera:
        this.obstacleActive4= new ArrayList<Obstacle>();
            //Parte de abajo
        this.obstacleActive4.add(new Obstacle(x-20, y-(6+50)));
        this.obstacleActive4.add(new Obstacle(x-20-5, y-(6+50)));
        this.obstacleActive4.add(new Obstacle(x-20-10, y-(6+50)));
            //Parte de arriba
        this.obstacleActive4.add(new Obstacle(x-20, y-(6+50+6)));
        this.obstacleActive4.add(new Obstacle(x-20-5, y-(6+50+6)));
        this.obstacleActive4.add(new Obstacle(x-20-10, y-(6+50+6)));

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
