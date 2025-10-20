# Calculator App

A modernized Java Swing calculator with a dark theme, rounded cards, and responsive button feedback. This folder contains the updated UI variant of the calculator with cleaner typography and a Material-inspired palette.

## Features
- Basic arithmetic: addition, subtraction, multiplication, division
- Decimal input and continuous calculations
- Clear (`C`) and delete (`DEL`) actions
- Division-by-zero handling with safe messaging
- Modern dark theme with rounded panels and accent buttons
- Hover and pressed feedback for tactile interactions

## Modern UI
- Nimbus look-and-feel with dark palette overrides via `UIManager`
- Rounded cards for display and keypad (`RoundedPanel`, radius 18)
- Distinct accents: teal for operators, green for equals, red for clear
- Contrast-first design: near-black panels, white text, subtle borders
- Typography: `Segoe UI` for a cleaner, readable look

## Requirements
- Java JDK 8 or newer
- No external libraries required

## Build & Run

```bash
javac CalculatorApp.java
java CalculatorApp
```

## Controls
- Digits: `0–9`
- Decimal: `.`
- Operators: `+`, `-`, `*`, `/`
- Equals: `=` computes the current expression
- Delete: `DEL` removes the last character
- Clear: `C` resets the expression and display

## Customize Theme
Open `CalculatorApp.java` and tweak these areas:
- Color palette constants: `BG`, `PANEL_BG`, `DISPLAY_BG`, `TEXT`, `BTN_BG`, `BTN_BORDER`, `OP_BG`, `EQ_BG`, `CLEAR_BG`
- Card rounding: `new RoundedPanel(18)` (increase for softer corners)
- Hover/press feel: adjust brightness in `installHoverEffect` and `brighten(...)`
- Spacing: `new GridLayout(4, 4, 10, 10)` controls gaps between buttons

Example palette overrides (set in `main` using `UIManager.put`):
```java
UIManager.put("control", BG);
UIManager.put("text", TEXT);
UIManager.put("nimbusBase", BG);
UIManager.put("nimbusBlueGrey", PANEL_BG);
UIManager.put("background", BG);
UIManager.put("Panel.background", BG);
```

## Project Structure
```
Calculator-GUI-Java/
├── CalculatorApp.java   # Modern dark-themed Swing calculator
└── README.md            # This document
```

## Troubleshooting
- If the app launches with a legacy look, Nimbus may not be active; ensure `UIManager.setLookAndFeel(...)` succeeds before `SwingUtilities.invokeLater(...)`.
- On some desktops, default button colors can be overridden by OS theme; the helpers (`styleBaseButton`, `styleAccentButton`, `styleNeutralButton`) reset backgrounds and borders for consistency.
- If you see rendering artifacts, verify panels that sit on rounded cards use `setOpaque(false)` so the card background shows through.

