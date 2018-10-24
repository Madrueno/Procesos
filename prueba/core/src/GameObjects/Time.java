package GameObjects;
import java.util.Timer;
import java.util.TimerTask;

public class Time {
    private Timer timer = new Timer();
    private int sec=0;

    class objectTime extends TimerTask { //Contador
        public void run() {
            sec++;
            System.out.println("Second: " + sec);
        }
    }

    public void addTime() //Creación del Timer y empieza a contar; //Contar
    {
        this.sec=0;
        timer = new Timer();
        timer.schedule(new objectTime(), 0, 1000);
    }

    public void Stop() { //Detiene el contador
        timer.cancel();
    }

    public int getSec()
    {
        return this.sec;
    }

    public boolean tenSec(){
        return (sec==10);
    }
}
