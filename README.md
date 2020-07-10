# ILikeToMowIt - Let's start mowing ! 
## Introduction

## Development details

### Step 1
The first step in my reasoning was to find a way to handle the direction of each mower. With that, I was thinking that 
using an ordinal value could help me with a simple operation to shift to the left or right position using an addition, and a modulo to have a circular pattern.

Since the current direction of a mower will also impact its next move, I found that using the current position of a mower with a fixed next step value was coherent and of the domain of a direction. 
That is why I used, for each direction, an xIncrement and yIncrement, that will come handy for the 'F' instruction. 

### Step 2
The second step of the process was to create the grid where all the mowers will battle to have a space to move to. 
This object will mainly be used to keep track of the available cells in the current and only grid.

It has a maxWidth and maxHeight, and the most important part is the 2 dimensional boolean array which is used to keep track of the used, or not, grid cells.
After initially setting up the boolean array with the position of each mower, this object provides some utility methods to tell the caller if the cell it wants to go in next time is available or not.

### Step 3
This is where it gets interesting. I decided that a mower has a position (x, y), a direction, a mower number (for the print in the last step), and also a list of instructions.
At the construction of the mowers, I parse the instruction list and put each instruction inside a FIFO queue.

Using the strategy pattern and a BiConsumer<Mower, Grid>, I can organize my code so that each instruction has an impact on a mower, for a grid. In this case I just go through each instruction of the mower's FIFO queue and each action is in order and doing the given checks on the only grid available.

Since the two-dimensional array grid occupation is synchronized, each mower can only claim an available grid cell.

### Misc

