import java.util.LinkedList;

public class Processor {
    final private int id;
    final private LinkedList<Task> tasks;

    public Processor(int id) {
        this.id = id;
        this.tasks = new LinkedList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask() {
        return tasks.poll();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public double getLoad() {
        return Math.min(1.0, tasks.size() * 0.2);
    }

    public int getId() {
        return id;
    }
}