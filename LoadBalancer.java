import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    final private List<Processor> processors;
    final private List<Task> taskQueue;

    public LoadBalancer() {
        processors = new ArrayList<>();
        taskQueue = new ArrayList<>();
        processors.add(new Processor(0));
        processors.add(new Processor(1));
    }

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public void addProcessor() {
        processors.add(new Processor(processors.size()));
    }

    public List<Processor> getProcessors() {
        return processors;
    }

    public void distribute() {
        for (Task task : new ArrayList<>(taskQueue)) {
            Processor leastLoaded = processors.get(0);
            for (Processor p : processors) {
                if (p.getTaskCount() < leastLoaded.getTaskCount()) {
                    leastLoaded = p;
                }
            }
            leastLoaded.addTask(task);
            taskQueue.remove(task);
        }
    }

    public void rebalance() {
        Processor maxLoaded = processors.get(0);
        Processor minLoaded = processors.get(0);
        for (Processor p : processors) {
            if (p.getTaskCount() > maxLoaded.getTaskCount()) {
                maxLoaded = p;
            }
            if (p.getTaskCount() < minLoaded.getTaskCount()) {
                minLoaded = p;
            }
        }
        if (maxLoaded.getTaskCount() - minLoaded.getTaskCount() >= 2) {
            Task task = maxLoaded.removeTask();
            if (task != null) {
                minLoaded.addTask(task);
            }
        }
    }

    public void showLoads() {
        System.out.println("Current Loads:");
        for (Processor p : processors) {
            double load = p.getLoad();
            int bars = (int) (load * 10); // 10-character bar
            StringBuilder bar = new StringBuilder("[");
            for (int i = 0; i < 10; i++) {
                bar.append(i < bars ? "#" : "-");
            }
            bar.append("] ");
            System.out.printf("Processor %d: %s %.0f%% (%d tasks)%n",
                    p.getId(), bar, load * 100, p.getTaskCount());
            if (load > 0.8) {
                System.out.println("Warning: Processor " + p.getId() + " overloaded!");
            }
        }
        System.out.println();
    }

    public void logLoads() {
        try (java.io.FileWriter writer = new java.io.FileWriter("metrics.txt", true)) {
            for (Processor p : processors) {
                writer.write("Processor " + p.getId() + ": " + (int) (p.getLoad() * 100) + "%\n");
            }
            writer.write("\n");
        } catch (Exception e) {
            System.out.println("Error writing to metrics.txt!");
        }
    }
    
}