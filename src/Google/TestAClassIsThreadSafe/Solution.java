import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Solution{
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    int var = 10;
    public int read(){
        rwl.readLock().lock();
        int tmp =  var;
        rwl.readLock().unlock();
        return tmp;
    }

    public void add(int val) throws InterruptedException {
        rwl.writeLock().lock();
        if(this.var + var >= 0){
            Thread.sleep(10);
            this.var += var;
        }
        rwl.writeLock().unlock();
    }
}
