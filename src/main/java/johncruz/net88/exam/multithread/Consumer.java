package johncruz.net88.exam.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Consumer extends Thread {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Producer producer;

    public Consumer(Producer producer){
        this.producer = producer;
    }

    @Override
    public void run() {
        try {
            while (true){
                String data = producer.consume();
                logger.info("Consumed by : {}; data : {}",Thread.currentThread().getName(),data);
                Thread.sleep(200);
            }
        } catch (Exception e){
        }
    }
}
