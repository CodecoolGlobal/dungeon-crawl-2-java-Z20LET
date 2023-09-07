# Blue Dungeon 2.0
### A JavaFX dungeon crawler game

## Introduction

This project was a school assignment at our programming school Codecool, of a dungeon crawler-style video game using the JavaFX library as well as an SQL database for saving progress. It was made by [Z20LET](https://github.com/Z20LET) and [SzimBensze](https://github.com/SzimBensze). Even though it is a demo, we consider it finished, however small patches may be released from time to time. Thank you for checking it out, but please only download and examine the code for personal purpouses. Do not copy and redistribute without our permission!

## Installation guide

You will need at least Java JRE 8 or JDK 16 to be able to execute the program. Both are recommended to be installed for a flawless gameplay. After installation, navigate [here](../development/out/artifacts/BlueDungeon2Demo) and download the folder's contents (JAR file and help text). Preferably, you may download the RAR file and use WinRAR to unzip the same contents. Please read the help text carefully for a gameplay introduction and other useful hints.

## How to play

After installing the necessary prerequisites, navigate to your downloads or wherever the JAR is downloaded and double click it to execute. If the window resolution is not acceptable, press F11 or Alt+Enter to switch to fullscreen. While in-game, press Q to exit. Please check the in-game instructions and the help text included with the game file fore more help. See the top right corner in the game window for controls.

![image](../development/screen1.PNG)

The player controls the blue character and with each movement all other entities move as well, like in a turn-based game.

![image](../development/screen2.PNG)

## Features, tech stack

The game's core is fully Java and the frontend is made by using the JavaFX library. You can save your progress if you create a PostgreSQL database called "dungeon", please follow the official Postgres documents for more instructions. If the SQL database is connected successfully to the game, it'll be able to save any progress after restarting the program. We used IntelliJ IDEA as work environment and we were mostly pair programming. Please send a notification to me (SzimBensze) if you're interested in more details of the game and it's technology!
