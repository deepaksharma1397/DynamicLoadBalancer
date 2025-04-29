# Dynamic Load Balancer

This is a Java-based console application that simulates a dynamic load balancer distributing tasks among multiple processors.

##  Features

- Add tasks with CPU cycles
- Dynamically add processors
- Automatically distribute and rebalance tasks
- Logs processor loads to metrics.txt
- Text-based load visualization in console

##  Project Structure

- `Main.java` – Command-line interface
- `LoadBalancer.java` – Core logic for distributing and rebalancing
- `Processor.java` – Represents a processor handling tasks
- `Task.java` – Represents an individual task
- `metrics.txt` – Logging file for tracking processor loads

##  How to Run

1. **Clone the repo**:
   ```bash
   git clone https://github.com/yourusername/dynamic-load-balancer.git
   cd dynamic-load-balancer

