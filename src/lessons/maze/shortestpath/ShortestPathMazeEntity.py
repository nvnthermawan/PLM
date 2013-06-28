def setX(i):
        errorMsg("Sorry Dave, I'm afraid I cannot let you use setX(i) in this exercise")
def setY(i):
        errorMsg("Sorry Dave, I'm afraid I cannot let you use setY(i) in this exercise")
def setPos(x,y):
        errorMsg("Sorry Dave, I'm afraid I cannot let you use setPos(x,y) in this exercise")

def setIndication(x, y, i):
    cell = entity.getWorld().getCell(x,y)
    cell.setContent(str(i))

def getIndication(x,y):    
    cell = entity.getWorld().getCell(x,y)
    if cell.hasContent():
        return int(cell.getContent())
    return 9999;

def hasBaggle(x, y):
    return entity.getWorld().getCell(x,y).hasBaggle() 
def hasTopWall(x, y):
    return entity.getWorld().getCell(x,y).hasTopWall() 
def hasLeftWall(x, y):
    return entity.getWorld().getCell(x,y).hasLeftWall() 

# BEGIN SOLUTION
def hasRightWall(x,y):
    return hasLeftWall((x + 1) % getWorldWidth(), y) 

def hasBottomWall(x, y):
    return hasTopWall(x, (y + 1) % getWorldHeight());

def setValueIfLess(x, y, val):
    if val < getIndication(x, y) :
        setIndication(x, y, val)
        return True

    return False

def evaluatePaths():
    # looking for labyrinth exit    
    for x in range(getWorldWidth()):
        for y in range(getWorldHeight()):     
            if hasBaggle(x,y):
                setIndication(x, y, 0);

    while (True):
        for x in range(getWorldWidth()):
            for y in range(getWorldHeight()):
                indication = getIndication(x, y)
                
                changed = False
                if indication != 9999 :

                    if not hasBottomWall(x,y):
                        changed = setValueIfLess(x, (y + 1) % getWorldHeight(), indication + 1) or changed

                    if not hasRightWall(x,y) :
                        changed = setValueIfLess((x + 1) % getWorldWidth(), y, indication + 1) or changed

                    if not hasTopWall(x,y) :
                        changed = setValueIfLess(x, (y+getWorldHeight() - 1) % getWorldHeight(), indication + 1) or changed

                    if not hasLeftWall(x,y) :
                        changed = setValueIfLess((x +getWorldWidth() - 1) % getWorldWidth(), y, indication + 1) or changed

                    if changed and x == getX() and y == getY():
                        return # reached the buggle, that's enough

def followShortestPath():
    while not isOverBaggle():
        x = getX()
        y = getY()

        topValue = 9999
        bottomValue = 9999
        leftValue = 9999
        rightValue = 9999

        if not hasTopWall(x,y):
            topValue = getIndication(x, (y + getWorldHeight() - 1) % getWorldHeight());

        if not hasBottomWall(x, y) :
            bottomValue = getIndication(x, (y+1) % getWorldHeight());

        if not hasLeftWall(x, y) :
            leftValue = getIndication((x +getWorldWidth() - 1) % getWorldWidth(), y);

        if not hasRightWall(x, y) :
            rightValue = getIndication((x + 1) % getWorldWidth(), y);
            
        if topValue <= bottomValue and topValue <= leftValue and topValue <= rightValue :
            setDirection(Direction.NORTH)
        elif rightValue <= topValue and rightValue <= bottomValue and rightValue <= leftValue :
            setDirection(Direction.EAST)
        elif leftValue <= rightValue and leftValue <= bottomValue and leftValue <= topValue :
            setDirection(Direction.WEST)
        elif bottomValue <= topValue and bottomValue <= rightValue and bottomValue <= leftValue :
            setDirection(Direction.SOUTH)

        forward();

evaluatePaths()      # write on each case the distance to the maze exit
followShortestPath() # make the buggle follow the shortest path
pickUpBaggle()       # enjoy the baggle!           

# END SOLUTION
