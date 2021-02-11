package johncruz.net88.exam.multithread;

public class ExecuteProducerConsumer {

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.setName("Producer 1");
        producer.start();
        Consumer consumer = new Consumer(producer);
        consumer.setName("Consumer 1");
        consumer.start();
        Consumer consumer2 = new Consumer(producer);
        consumer2.setName("Consumer 2");
        consumer2.start();
    }
}
