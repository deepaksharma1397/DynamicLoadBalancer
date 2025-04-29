import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoadBalancer balancer = new LoadBalancer();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Dynamic Load Balancer");
        System.out.println("1: Add Task | 2: Add Processor | 3: Exit");

        while (running) {
            System.out.print("Enter choice (1-3): ");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                System.out.print("Enter CPU cycles (e.g., 300): ");
                String cyclesInput = scanner.nextLine();
                if (cyclesInput.matches("\\d+")) {
                    int cycles = Integer.parseInt(cyclesInput);
                    balancer.addTask(new Task((int) (Math.random() * 100), cycles));
                    balancer.distribute();
                    balancer.rebalance();
                    balancer.showLoads();
                    balancer.logLoads();
                } else {
                    System.out.println("Invalid number! Enter digits only.");
                }
            } else if (input.equals("2")) {
                balancer.addProcessor();
                System.out.println("Added Processor " + (balancer.getProcessors().size() - 1));
                balancer.showLoads();
                balancer.logLoads();
            } else if (input.equals("3")) {
                running = false;
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice! Enter 1, 2, or 3.");
            }
        }
        scanner.close();
    }
}