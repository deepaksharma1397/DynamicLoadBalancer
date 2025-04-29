public class Task {
   final private int id;
   final private int cpuCycles;

    public Task(int id, int cpuCycles) {
        this.id = id;
        this.cpuCycles = cpuCycles;
    }

    public int getCpuCycles() {
        return cpuCycles;
    }
}