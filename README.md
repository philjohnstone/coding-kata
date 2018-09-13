# coding-kata

## berlin-clock

A Berlin Clock uses lights to represent hours, minutes and odd/even seconds of a 24h clock. E.g.
![Berlin Clock](https://upload.wikimedia.org/wikipedia/commons/4/4f/Berlin-Uhr-1650-1705.gif)

The top light represents whether the seconds passed the minute are even (lit) or odd (off). 
The next row contains four lights which represent how many five hours of the day have passed.
The third row contains four lights which represent the remaining single hours (e.g. 17 hours = 3 lights on row 2 and 2 lights on row 3)
The next row contains eleven lights which represent how many five minutes of the hour have passed. Each third light (15 minutes) is red.
The final row contains four lights which represent the remaining single minutes.

The kata ([taken from agilekatas.co.uk](http://agilekatas.co.uk/katas/BerlinClock-Kata)) is implemented progressively (test, implement, refactor):
1. Implement single minutes (row 5)
2. Implement five minutes (row 4)
3. Implement single hours (row 3)
4. Implement five hours (row 2)
5. Implement seconds lamp (row 1)
6. Integrate whole clock

## shopping-cart

A shopping cart sells items at the correct price and eventually allows for creation of discounts.

The kata ([taken from codereview.stackexchange](https://codereview.stackexchange.com/questions/161134/shopping-cart-design-interview-task)) is implemented using:
1. The shopping cart only sells oranges and apples
2. Apples cost 60p, oranges cost 25p
3. Build a system that takes a list of items and returns a total
4. Add the offer of buy one, get one free on apples
5. Add the offer of three oranges for the price of two

## bowling

Calculate the score for bowling.

The kata ([loosely taken from http://butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata]Uncle Bob) has the following conditions:
1. The pins knocked down from sequential incomplete frames are summed
2. A spare gives a frame a score of 10 plus a bonus of the next roll
3. A strike gives a frame a score of 10 plus a bonus of the next two rolls
4. A perfect game gives the score of 300
