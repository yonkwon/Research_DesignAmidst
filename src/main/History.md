This markdown document summarizes the history of changes in the corresponding model to help error tracing and model restoration.

## Idea
* Apply BFS to avoid a disconnected network
  * https://www.geeksforgeeks.org/bridge-in-a-graph/
  * However, this will remove the risk of disconnection from the story

## History
* 240311
  * DAFormBreak
    * Two braking rules
      * Breaking Rule 1. degree[focal] >= Main.MAX_DEGREE
        * Performance result:
        * Isolates: Exist for many scenarios. Particularly severe when c0.1 e0.0
      * Breaking Rule 2. degree[focal] > 1
 