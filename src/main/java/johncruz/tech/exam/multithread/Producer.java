package johncruz.tech.exam.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int MAX_SIZE = 3;

    private final List<String> messages = new ArrayList<>();

    @Override
    public void run() {
        try {
            while (true){
                produce();
            }

        } catch (Exception ex){

        }
    }

    private synchronized void produce() throws InterruptedException {
        while (messages.size() == MAX_SIZE){
            logger.info("Queue Limit reached.");
            wait();
        }
        String data = LocalDateTime.now().toString();
        messages.add(data);
        logger.info("Produced data.");

        notify();
    }

    public synchronized String consume() throws InterruptedException {
        notify();
        while (messages.isEmpty()){
            wait();
        }
        String data = messages.get(0);
        messages.remove(0);
        return data;
    }
}
