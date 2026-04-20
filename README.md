# TPintegradorAlgoritmos
## About The Project

This repository contains a comprehensive, text-based RPG developed in Java. The game runs entirely in the console and offers a rich player experience with classic RPG elements. Players create a character, choose a profession, and navigate a world filled with challenges, mini-games, and opportunities for wealth and progression.

The game features a persistent world where player progress is saved automatically, allowing for continuous gameplay across sessions. A global leaderboard tracks the most successful players, fostering a competitive environment.

## Features

*   **Character Progression**: Advance your character by gaining experience, leveling up, and improving stats like health and damage.
*   **Rebirth System**: Utilize the `/rebirth` command to reset your progress in exchange for permanent multipliers on earnings and sales, enabling a "New Game+" or prestige-style of play.
*   **Class-Based Gameplay**: At the start of the game, choose one of seven unique jobs:
    *   **Leñador (Lumberjack)**: Stable, reliable income.
    *   **Minero (Miner)**: RNG-based income with a small chance for a massive payout.
    *   **Mercader (Merchant)**: Low active income, but gains a massive bonus when selling items.
    *   **Mercenario (Mercenary)**: High early-game income that diminishes at higher levels.
    *   **Aristocrata (Aristocrat)**: Low early-game income that scales exponentially with level.
    *   **Ladron (Thief)**: High-risk, high-reward role with a chance to steal large sums or suffer heavy penalties.
    *   **Tahur (Gambler)**: Minimal active income, but receives significant passive bonuses to luck and gambling wins.
*   **Turn-Based Combat**: Engage in PvE combat against a variety of creatures like wolves, dragons, and skeletons using the `/cazar` command. The combat system features a dice-roll mechanic to determine damage multipliers.
*   **Interactive Mini-Games**: Explore the world with `/explorar` to encounter random events and mini-games:
    *   **Ahorcado (Hangman)**: Guess the word before you run out of health.
    *   **Preguntado (Trivia)**: Test your knowledge with a wide range of questions.
    *   **Buscaminas (Minesweeper)**: A logic puzzle to find and flag hidden mines on an 8x8 grid.
*   **Economy and Items**:
    *   Visit the `/tienda` to buy and sell items.
    *   Manage your possessions in the `/inventario`.
    *   Find and use `Consumibles` (like health potions), `Equipables` (weapons and armor), and `Desechables` (loot to sell for profit).
*   **Gambling**: Take a risk at the casino with `/blackjack`, a fully functional Blackjack game where you can bet your hard-earned coins.
*   **Persistence & Leaderboard**: Your game is automatically saved when you exit. Compare your wealth, level, and rebirths against other players on the local `/leaderboard`.

## Getting Started

This is a console-based application. To play, compile and run the `Main.java` file.

1.  Launch the application.
2.  You will be prompted to enter a username. If a save file with that name exists, your game will be loaded. Otherwise, a new character will be created.
3.  New players will be guided through an interactive tutorial that introduces the job selection system and basic commands like `/trabajar`, `/tienda`, and `/inventario`.
4.  Gameplay is driven by entering text commands in the console. Use `/comandos` to see a full list of available actions.

## Core Commands

The game is controlled via text commands. Here are some of the essential ones:
-----------------------------------------------------------------------------------------------------
| Command        | Alias(es)   | Description                                                        |
|----------------|-------------|--------------------------------------------------------------------|
| `/trabajar`    | `/t`, `/work` | Perform your character's job to earn money and experience.       |
| `/explorar`    | `/e`        | Embark on an adventure to find a random mini-game or event.        |
| `/cazar`       | `/cz`       | Hunt a random monster for loot, money, and experience.             |
| `/tienda`      | `/shop`     | Access the shop to buy and sell items.                             |
| `/inventario`  | `/i`        | View your character's stats, equipped items, and inventory.        |
| `/blackjack`   | `/bj`       | Play a game of Blackjack to win money.                             |
| `/rebirth`     | `/renacer`  | Reset your character's progress for powerful permanent bonuses.    |
| `/leaderboard` | `/l`, `/j`  | Display the global player rankings.                                |
| `/comandos`    | `/c`, `/help` | Show the list of all available commands.                         |
| `/alias`       | `/a`        | Show the list of command shortcuts.                                |
| `/salir`       | `/s`        | Save your progress and exit the game.                              |
-----------------------------------------------------------------------------------------------------
