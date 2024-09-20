# Klondike-Solitaire v1.0

## Overview

A classic Klondike Solitaire game inspired by the Windows XP version that I developed in 2004 using the Swing framework.

## Requirements and Setup

Java Runtime Environment (JRE) 1.8

Note: The program is optimized for JRE 1.8 and may not run correctly on newer versions.

To start the application, run the `KlondikeSolitaire.java` file located in the `org.example` package.

## The purpose of the game

The objective of Solitaire is to arrange all the cards from the deck into ascending sequences by suit, from Ace to King.

## Game rules

- At the start of the game, part of the deck is dealt into seven piles, while the rest of the cards remain in the stockpile.
- The top, face-up cards of the piles represent the first elements of their respective sequences, and only cards of a lower rank and different color can be placed on them. For example, a black two (clubs or spades) can be placed on a red three (hearts or diamonds).
- In the upper right corner of the table are the four foundation piles, where cards must be placed in ascending order, starting from the Ace up to the King. It's important that each foundation pile contains cards of the same suit. For example, only a seven of hearts can be placed on a six of hearts.
- Cards can be moved in several ways. If you double-click a card that can be placed on one of the foundation piles, it will automatically jump to the correct position. Pressing the right mouse button will automatically move all cards that can be placed on the foundation piles. You can also move cards by dragging them from one place to another if they fit in terms of rank and color. Multiple cards can be moved at once from one sequence to another if you pick a card that isn't on the top of the sequence. In this case, the selected card and all the cards above it can be dragged together to the appropriate position.
- After moving a sequence, the top card of the piles can be flipped by clicking on them.
- Drawing from the stockpile works similarly: the top card from the stockpile moves next to it, where it can then be moved into one of the sequences or foundation piles.
- If all cards from the stockpile have been flipped, click on the stockpile's space to reset the deck, allowing you to draw from the beginning again.
- When a pile is empty, a new sequence starting with a King can be placed in that space.
- To undo the last card moved, select the Undo option from the game menu.

## Scoring Systems

The game offers three different scoring systems to choose from:

__Normal__

- Placing a card into a foundation: 10 points
- Moving a card from the stockpile to a tableau pile: 5 points
- Flipping the top card of a pile: 5 points
- Moving a card from a foundation back to a tableau pile: -15 points
- Resetting the stockpile: -100 points
- In a timed game, for every 10 seconds that pass: -2 points
- You lose 2 points for each undo!

__Vegas__

- You start the game with a $52 debt (the wager)
- Placing a card into a foundation: 5 points
- Moving a card from a foundation back to a tableau pile: -5 points
- Once you've reached the end of the stockpile, you can't reset it.

__None__

- The only goal of the game is to move all the cards to their correct places.
- You can play with or without a timer.

## Settings

__Scoring System__

You can choose between two different scoring systems, which are described in the previous section.

__Cumulative Scoring__

This option is only available when using the Vegas scoring system!
When selected, at the start of each new game, the initial wager (-$52) is added to your accumulated winnings, so you begin the next game with your current total.

__Timed Game__

When enabled, a timer appears in the status bar, showing the time elapsed since the first click.
If the Normal scoring system is active, the passing time also affects your score!

__Status Bar__

You can choose whether to display the information section below the game table, which shows the elapsed time, score, and descriptions of the menu items during the game.

__Card Back__

The card backs can be chosen from 12 different designs, which can be browsed through in a dialog window. You can select an image using either mouse clicks or arrow keys.

__Themes:__

The game interface can be changed to three different "themes," each providing a different appearance. The available themes are:

- Windows
- Metal
- CDE/Motif

## Screenshots

<img src=docs/07.png alt="" width="400">
<br>
<img src=docs/01.png alt="" width="400">
<br>
<img src=docs/02.png alt="" width="400">
<br>
<img src=docs/03.png alt="" width="400">
<br>
<img src=docs/04.png alt="" width="400">
<br>
<img src=docs/05.png alt="" width="400">
<br>
<img src=docs/06.png alt="" width="400">