# ILikeToMowIt - Let's start mowing ! 
## Introduction

This is a plain Java 11 project.

Using gradle, you can run the project with the following command with the path of the file you wish to test :
> ./gradlew run --args="src/main/resources/inputNoMoves.txt" 

Or use the default input file given in the exercice statement :
> ./gradlew run

## Development details

### Step 1 - Get directions
The first step in my reasoning was to find a way to handle the direction of each mower. With the creation of the Direction object, I was thinking that 
using an ordinal value could help me with a simple operation to shift to the left or right position using an addition, and a modulo to have a circular pattern.

Since the current direction of a mower will also impact its next move, I found that using the current position of a mower with a fixed next step value was coherent and of the domain of a direction. 
That is why I used, for each direction, an xIncrement and yIncrement, that will come handy for the 'F' instruction. 

### Step 2 - Build the grid
The second step of the process was to create the grid where all the mowers will battle to have a space to move to. 
This object will mainly be used to keep track of the available cells in the current and only grid.

It has a maxWidth and maxHeight, and the most important part is the 2 dimensional boolean array which is used to keep track of the used, or not, grid cells.
After initially setting up the boolean array with the position of each mower, this object provides some utility methods to tell the caller if the cell it wants to go in next time is available or not. So that it can take the cell, or not move at all.

### Step 3 - Mowers are taking the world by storm
This is where it gets interesting. I decided that a mower has a position (x, y), a direction, a mower number (for the print in the last step), and a queue of instructions.
At the construction of the mowers, I parse the instruction list and put each instruction inside a FIFO queue.

Using the strategy pattern and a BiConsumer<Mower, Grid>, I can organize my code so that each instruction has an impact on a mower, for a given grid. In this case I just go through each instruction of the mower's FIFO queue and each action is in order and is doing the given checks on the only grid available.

Since the two-dimensional array grid occupation is synchronized, each mower can only claim an available grid cell.

### Step 4 - Finalize
This is the end game, finalize the project with the main method, the utils to build the grid and the mowers from the List<String> from previously parsed files. Here I launch each mower inside a work-stealing thread pool. With that fancy name it does the job well, being basically a ForkJoinPool, but I used it because it uses the Runtime.getRuntime().availableProcessors(). 

### Misc
I could have used a logger for the final step, but here I thought that since I did not use any external package for the project's implementation, I won't add one for that, and keep it that way.

Thank you for reviewing my code, I will gladly take any comment to improve myself.