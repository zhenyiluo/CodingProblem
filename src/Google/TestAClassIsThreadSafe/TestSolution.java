import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eric on 2015-10-25.
 */
public class TestSolution {
    static Solution solution = new Solution();
    public static void main(String[] args){
        ExecutorService executor1 = Executors.newFixedThreadPool(10);
            executor1.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            solution.add(-25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (solution.read() < 0) {
                            System.out.println("Something is wrong");
                        }
                    }
                }
            });

        ExecutorService executor2 = Executors.newFixedThreadPool(10);
        executor2.execute(new Thread(new Runnable() {
            @Override
            public void run(){
                while(true){
                    try {
                        solution.add(24);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(solution.read() < 0){
                        System.out.println("Something is wrong");
                    }
                }
            }
        }));
    }
}
