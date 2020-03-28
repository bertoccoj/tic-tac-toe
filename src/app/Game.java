package app;

public class Game extends Engine {
  public static void main(String[] args) { new Game(); }
  public Game() { super(); }

  int blockLenght;
  int spacing;
  boolean isCross;
  String winner = null;
  static String[][] board = {
    {"", "", ""},
    {"", "", ""},
    {"", "", ""},
  };

  @Override
  void setup() {
    createWindow(400, 400);
    blockLenght = width / 3;
    spacing = 20;
    isCross = true;
    background(255, 255, 255);
  }

  @Override
  void draw() {
    drawTable();
    drawSymbols();
    blockLenght = width / 3;

    if (winner != null) {
      sleep(100);
      endGameScreen(false);
    } else if (isDraw()) {
      sleep(1000);
      endGameScreen(true);
    } else {
      verifyWinner();
    }
  }

  @Override
  void onMouseClick() {
    int x = mouseX / blockLenght;
    int y = mouseY / blockLenght;

    if (board[y][x] != "") { return; }

    board[y][x] = isCross ? "x" : "o";
    isCross = !isCross;
  }

  @Override
  void onWindowResize() {
    resetCanvas();
  }

  void endGameScreen(boolean draw) {
    repeat(5, 1000, (Integer i) -> {
      resetCanvas();
      text("reiniciando em " + Integer.toString(i + 1), width / 2, height / 2 - 20);
      if (draw) {
        text("EMPATE", width / 2, height / 2);
      } else {
        text("VENCEDOR: " + winner, width / 2, height / 2);
      }
      return false;
    });
    restartGame();
  }

  void restartGame() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = "";
      }
    }
    winner = null;
    isCross = true;
    resetCanvas();
  }

  void drawTable() {
    // linhas horizontais
    drawLine(0, blockLenght, width, blockLenght);
    drawLine(0, blockLenght * 2, width, blockLenght * 2);

    // linhas verticais
    drawLine(blockLenght, 0, blockLenght, height);
    drawLine(blockLenght * 2, 0, blockLenght * 2, height);
  }

  void drawSymbols() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        switch (board[i][j]) {
          case "x": drawCross(i, j); break;
          case "o": drawBall(i, j); break;
        }
      }
    }
  }

  void drawCross(int rowIndex, int colIndex) {
    color(0, 0, 255);
    drawLine(
      colIndex * blockLenght + spacing,
      rowIndex * blockLenght + spacing,
      (colIndex + 1) * blockLenght - spacing,
      (rowIndex + 1) * blockLenght - spacing
    );

    drawLine(
      (colIndex + 1) * blockLenght - spacing,
      (rowIndex) * blockLenght + spacing,
      (colIndex) * blockLenght + spacing,
      (rowIndex + 1) * blockLenght - spacing
    );
    resetColor();
  }

  void drawBall(int rowIndex, int colIndex) {
    color(255, 0, 0);
    int x = (colIndex * blockLenght) + spacing;
    int y = (rowIndex * blockLenght) + spacing;
    int width = blockLenght - (spacing * 2);
    int height = blockLenght - (spacing * 2);

    drawEllipse(x, y, width, height);
    resetColor();
  }

  boolean isDraw() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == "") {
          return false;
        }
      }
    }
    return true;
  }

  void verifyWinner() {
    for (int i = 0; i < board.length; i++) {
      if (board[i][0] == "") { continue; }
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
        winner = board[i][0];
      }
    }

    for (int i = 0; i < board.length; i++) {
      String firstRow = board[0][i];
      String secondRow = board[1][i];
      String thirdRow = board[2][i];
      if (firstRow == "") { continue; }
      if (firstRow == secondRow && secondRow == thirdRow) {
        winner = firstRow;
      }
    }

    if (board[1][1] == "") { return; }

    if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      winner = board[0][0];
    }

    if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      winner = board[0][2];
    }
  }

}
