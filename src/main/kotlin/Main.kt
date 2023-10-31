import java.util.*

fun main(args: Array<String>) {
    val GAME_MENU = """
        1. New Game
        2. Exit
    """.trimIndent()
    var gameBoard = arrayOf(
        arrayOf(0, 0, 0),
        arrayOf(0, 0, 0),
        arrayOf(0, 0, 0)
    )
    val GAME_INSTRUCTIONS = """
        Tic Tac Toe
        -----------
        1. The game is played on a grid that's 3 squares by 3 squares.
        2. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares.
        3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.
        4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.
        5. To place your mark, enter the row and column number of the square you want to place your mark.
        6. Rows (Top to bottom) and columns (Left to right) are numbered from 1 to 3.
    """.trimIndent()
    var playerOne = Player("Player One", 1)
    var playerTwo = Player("Player Two", 2)
    var isRunning = true
    val scanner = Scanner(System.`in`)
    var currentPlayer = playerOne
    var currentSymbol = currentPlayer.symbol
    var input: String

    while (isRunning) {
        println(GAME_INSTRUCTIONS)
        println()
        println(GAME_MENU)
        input = scanner.nextLine()
        when (input) {
            "1" -> {
                println("Starting new game...")
                printGameBoard(gameBoard)
                while (true) {
                    println("${currentPlayer.name}'s turn")
                    println("Enter row: ")
                    val row = scanner.nextInt() - 1
                    println("Enter column: ")
                    val col = scanner.nextInt() - 1
                    gameBoard[row][col] = currentSymbol
                    printGameBoard(gameBoard)
                    if (currentPlayer == playerOne) {
                        currentPlayer = playerTwo
                        currentSymbol = currentPlayer.symbol
                    } else {
                        currentPlayer = playerOne
                        currentSymbol = currentPlayer.symbol
                    }

                    when (isSolved(gameBoard)) {
                        true -> {
                            isRunning = false
                            break
                        }
                        false -> {
                            continue
                        }
                    }
                }
            }
            "2" -> {
                println("Exiting game...")
                isRunning = false
            }
            else -> {
                println("Invalid input")
            }
        }
    }
}

fun isSolved(gameBoard: Array<Array<Int>>): Boolean {
    val result: Vector<Int> = Vector<Int>()

    result.add(gameBoard[0][0] * gameBoard[1][1] * gameBoard[2][2])
    result.add(gameBoard[0][2] * gameBoard[1][1] * gameBoard[2][0])

    for (i in 0..2) {
        result.add(gameBoard[i][0] * gameBoard[i][1] * gameBoard[i][2])
        result.add(gameBoard[0][i] * gameBoard[1][i] * gameBoard[2][i])
    }

    for (i in 0..3) {
        if (result[i] == 1) {
            println("Player One wins!")
            return true
        } else if (result[i] == 8) {
            println("Player Two wins!")
            return true
        } else if (result[i] == 0) {
            return false
        } else {
            println("It's a tie!")
            return true
        }
    }
    return false
}

fun printGameBoard(gameBoard: Array<Array<Int>>) {
    println("Game Board")
    println("---------")
    println()
    for (row in gameBoard) {
        for (col in row) {
            print("$col ")
        }
        println()
    }
}

data class Player(val name: String, val symbol: Int)