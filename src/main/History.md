This markdown document summarizes the history of changes in the corresponding model to help error tracing and model restoration.

## Idea
* Apply BFS to avoid a disconnected network
  * https://cp-algorithms.com/graph/bridge-searching.html
  * For the current model this doesn't work because every edge of a hirerachy is a bridge... 

## History
* 240311
  * DAFormBreak
    * Two braking rules
      * Breaking Rule 1. degree[focal] >= Main.MAX_DEGREE
        * Performance result: Very interesting at e=0.5: random rewiring bad; netowrk dynamic good.
        * Isolates: Exist for many scenarios. Particularly severe when c0.1 e0.0
      * Breaking Rule 2. degree[focal] > 1
 