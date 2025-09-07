# Solitaire

A complete Java implementation of the classic Solitaire card game with a graphical user interface.

## 🎮 Overview

Solitaire is a fully functional Solitaire game built in Java using Swing for the graphical interface. The game implements the standard Klondike Solitaire rules with a clean, intuitive interface featuring card images and visual feedback.

## ✨ Features

- **Complete Solitaire Gameplay**: Full implementation of Klondike Solitaire rules
- **Graphical Interface**: Clean Swing-based GUI with card images
- **Interactive Gameplay**: Click-to-move interface with visual selection feedback
- **Card Management**: Proper card shuffling, dealing, and movement validation
- **Visual Assets**: Complete set of card images (GIF format) for all suits and ranks
- **Game Logic**: Comprehensive rule enforcement for valid moves

## 🎯 Game Rules

The game follows standard Klondike Solitaire rules:

- **Objective**: Move all cards to the four foundation piles, built up by suit from Ace to King
- **Tableau**: Seven piles where cards are built down by alternating colors
- **Stock**: Draw pile that deals three cards at a time to the waste pile
- **Waste**: Face-up cards from the stock pile
- **Foundations**: Four piles for each suit (Clubs, Diamonds, Hearts, Spades)

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, VS Code) or command line tools

### Running the Game

1. **Clone the repository**:
   ```bash
   git clone <your-repo-url>
   cd solitaire
   ```

2. **Compile the Java files**:
   ```bash
   javac *.java
   ```

3. **Run the game**:
   ```bash
   java Solitaire
   ```

### Alternative: Using an IDE

1. Open the project in your preferred Java IDE
2. Ensure all `.java` files are in your source path
3. Ensure the `cards/` directory is in your project root
4. Run the `Solitaire.java` file

## 📁 Project Structure

```
solitaire/
├── Solitaire.java          # Main game logic and controller
├── SolitaireDisplay.java   # GUI implementation and rendering
├── Card.java              # Card class with properties and methods
├── cards/                 # Card image assets
│   ├── back.gif          # Card back image
│   ├── [rank][suit].gif  # Individual card images
│   └── ...               # Complete deck of cards
└── README.md             # This file
```

## 🎮 How to Play

1. **Starting the Game**: The game automatically deals cards into seven tableau piles
2. **Drawing Cards**: Click the stock pile (top-left) to draw three cards to the waste pile
3. **Moving Cards**: 
   - Click on cards to select them (they'll be highlighted in yellow)
   - Click on valid destinations to move cards
   - Cards can be moved between tableau piles, to foundations, or from waste
4. **Building Sequences**: 
   - Tableau: Build down by alternating colors (red on black, black on red)
   - Foundations: Build up by suit from Ace to King
5. **Winning**: Move all cards to the four foundation piles to win!

## 🔧 Technical Details

### Architecture

- **Model-View Pattern**: Separation of game logic (`Solitaire.java`) and display (`SolitaireDisplay.java`)
- **Object-Oriented Design**: Clean class structure with `Card` objects
- **Event-Driven**: Mouse click handling for user interactions

### Key Classes

- **`Solitaire`**: Main game controller managing game state, rules, and card movements
- **`SolitaireDisplay`**: Swing-based GUI handling rendering and user input
- **`Card`**: Represents individual playing cards with rank, suit, and face-up state

### Game Components

- **Stock Pile**: Shuffled deck of 52 cards
- **Waste Pile**: Face-up cards drawn from stock
- **Foundation Piles**: Four piles for each suit (Ace to King)
- **Tableau Piles**: Seven piles for building sequences

## 🎨 Visual Features

- **Card Images**: High-quality GIF images for all 52 cards plus card back
- **Visual Feedback**: Yellow border highlighting for selected cards
- **Cascading Layout**: Cards in tableau piles are offset for better visibility
- **Green Background**: Classic Solitaire table appearance

## 🛠️ Development Notes

- **Author**: Aidan Wang
- **Version**: November 3, 2023
- **Language**: Java with Swing GUI
- **Card Images**: GIF format for compatibility and small file sizes

## 📝 License

This project is open source. Feel free to use, modify, and distribute according to your needs.

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs or issues
- Suggest new features
- Submit pull requests
- Improve documentation

## 🎯 Future Enhancements

Potential improvements could include:
- Score tracking
- Undo functionality
- Different Solitaire variants
- Sound effects
- Animation improvements
- Save/load game states

---

**Enjoy playing Solitaire!** 🃏
