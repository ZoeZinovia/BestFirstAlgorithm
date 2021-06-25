# Blocks World - Artificial Intelligence

This project implements an AI agent that can play the Box Worlds game using the Best First Algorithm.

## Description

Blocks World is one of the most famous planning games in AI. The game typically consists of a 1-dimensional surface, e.g. table, and n 2-dimensional blocks that can be moved vertically and horizontally. The game has an initial configuration of blocks, i.e. where blocks are stacked, and a final configuration. 

The aim is to move the blocks in such a way that the final configuration is recreated with the minimum number of block movements. A block can only be moved if it does not have another block on top of it. With a small n value, e.g. 3 blocks, the problem is easy to solve. However, with a large n value, the problem becomes NP-hard. 

![Image showing Blocks World setup](https://www.researchgate.net/profile/William-Kennedy-17/publication/228946818/figure/fig1/AS:669429281067019@1536615786725/A-Blocks-World-Problem.png)

This project solves the Blocks World problem using the Best First Algorithm and n = 3. With larger n values, another algorithm, e.g. Heuristic state-space algorithm, should be used.

## Getting Started

### Dependencies

* Java 8 is the only requirement. 

### Installing and using

* Simply clone the code from this repository. If using Eclipe, IntelliJ or another IDE, main.java can be run.
* If running on terminal, src code needs to be compiled before running Main. This can be done by navigating into the src directory of the cloned the repository. Then run: ```javac -d ./../bin -sourcepath . Main.java ```. Then the application can be run from the bin directory with the following command: ```java Main```
* The program expects two lines of input. The first line provides the initial configuration of the blocks and the second line provides the final configuration. The output is the configuration at each step on the path from the initial to the final configuration as well as the number of steps needed.
* Here is an example of the input:
```
   >> java Main
   >> CAB
   >> AB C
```
* And here is the expected output:
```
  [C, A, B]
  
  [C, A]
  [B]
  
  [C]
  [B]
  [A]
  
  [C]
  [A, B]
  
  3
```
