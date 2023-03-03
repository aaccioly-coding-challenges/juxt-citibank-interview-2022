# JUXT / Citibank coding challenge

This is a solution for two coding challenges I've completed for JUXT and Citibank while interviewing for a contract
role.

## How to build

The project uses Gradle as a build tool. To build the project, run:

    ./gradlew build

## How to run tests

To run the tests, run:

    ./gradlew test

To rerun the tests when all tasks are up-to-date, run:

    ./gradlew --no-build-cache cleanTest test

This project uses Adarsh Ramamurthy's [Gradle Test Logger Plugin](https://github.com/radarsh/gradle-test-logger-plugin)
to produce more readable test output.

## The challenge

I've completed two rounds of live coding exercises March, 2022.  
The first round was a 45 minutes design / coding exercise, with one of JUXT's engineers.
The second was an hour-long coding exercise with a Citibank engineer.

On both occasions I was being evaluated on my ability to design and code a solution to a problem, my ability to
communicate my thought process and my ability to work with a partner adhering to TDD practices. I was also evaluated on
my Kotlin skills.

The objective of the exercise wasn't to finish the requested features, but to demonstrate my ability to work. It also
not a representation of how I would write production code in a real-world scenario. It lands somewhere in between
LeetCode and a TDD Kata. The most realistic aspect of the exercises was the time pressure and the way that it tested my
ability to bend the rules and simplify the problem to make it more manageable (something that is a real specialty of
mine after a couple of decades working in Tech).

I was successful in both rounds and was offered a contract position with JUXT allocated at Citibank.

### First round: Blackjack

I was asked to design and code a solution to a Blackjack game. There weren't any upfront requirements, and I asked
clarification questions as I went along.

Here's what me and interviewer and I came up with:

* The game is played with a single deck of 52 cards
* A deck is shuffled before each game
* A player can draw cards from the deck
* A card can be either a number card or a face card
    * Number cards have a value equal to their number
    * Face cards have a value of 10
    * Aces can be either 1 or 11.
* A hand is a collection of cards held by a player
* Two aces can either add up to 2 or 12. The value of the hand is the highest possible value without going over 21.

I got as far as implementing the hand value calculation, but stopped short of implementing the game logic because the
interviewer was satisfied.

### Second round: Change-making problem

I was asked to design a Vending Machine that can make change for a given amount of money.
Just like in the first round, there weren't any upfront requirements.

The interviewer and I ended agreeing on the following:

* The vending machine can vend items for a given price
* The vending machine can accept coins of different values
* The vending machine can make change for a given amount of money
* The vending machine holds unlimited number of coins of each value
* Change only has to work for canonical coin systems

The last constraint greatly simplified the problem, allowing me to use a greedy algorithm instead of the classical
dynamic programming solution.

## Post interview improvements

1. I've cleaned up the Gradle build and updated dependencies (multiple times)
2. AssertJ was replaced with Kotest core assertions
3. The project was renamed to reflect the repository name
4. I wrote this README with my recollection of the events, so it might not be 100% accurate
5. I've added GitHub Actions to run the tests on every push and pull request
6. Dependabot was enabled to keep dependencies up-to-date
7. Added value classes for `Coin` and `Amount`
8. Enabled Gradle Build Scans
9. Added support for partial change-making
10. Added support for change-making for non-canonical coin systems

