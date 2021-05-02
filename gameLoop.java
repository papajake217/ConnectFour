import java.util.Scanner;

/*
BUGS: possibly fixed issues with winning by fixing logic, maybe convert the standard integers to lengths in relation to the board array
Up to date with TestingAi's win logic, use it more to test and bugfix.
 */
public class gameLoop {
    //define empty board, default values are empty (0)
    public static int[][] board = new int[6][7];

    //int signifying whether its red or yellows turn, 0 is red, 1 is yellow
    public static int whosTurn = 1;

    public static boolean noWinner = true;

    public static Scanner sc = new Scanner(System.in);
    //keep track of turns to check for no winner
    public static int turnCount = 0;
    public static boolean tie = false;

    public static void main(String[] args){
        printScreen();
        while(noWinner) {
            swapTurn();
            playerMove();
            doTurn();
            printScreen();
            checkWin();
        }

        result();

    }


    //function to print the screen after each turn
    public static void printScreen(){
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                System.out.print("| ");
                switch(board[i][j]){
                    case 1:
                        System.out.print("R ");
                        break;
                    case 2:
                        System.out.print("Y ");
                        break;
                    default:
                        System.out.print("  ");
                }
            }
            System.out.print("|");
            System.out.print("\n");
        }
    }

    //method that displays text and specifies whose turn it is
    public static void playerMove(){
        String player;
        if(whosTurn == 0){
            player = "red";
        } else{
            player = "yellow";
        }
        System.out.print("Drop a " + player + " disk at column (0-6): ");
    }

    //method that takes user input and performs the turn
    public static void doTurn(){
        int column = sc.nextInt();
        while(column < 0 || column > 6 || board[0][column] != 0) {
            if (board[0][column] != 0) {
                System.out.println("Error, column is full. Input a new column: ");
            } else {
                System.out.println("Error, invalid column number. Input a new column: ");
            }
            column = sc.nextInt();
        }
        //count until finds empty space
        int counter = -1;
        for(int i = 0;i < board.length;i++){
            if(board[i][column] != 0){
                break;
            }
            counter++;
        }

        //fill in array at the lowest empty space found
        if(whosTurn == 0){
            board[counter][column] = 1;
        } else{
            board[counter][column] = 2;
        }

    }

    //method to alternate the turns
    public static void swapTurn(){
        if(whosTurn == 0){
            whosTurn = 1;
        } else{
            whosTurn = 0;
        }
    }

    //method that checks for every win condition
    public static void checkWin(){
        turnCount++;
        //3 win conditions

        //4 horizontal non-empty matches
        for(int i=0;i<=5;i++){
            for(int j=0;j<=3;j++){
                if(board[i][j] != 0 && board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3]){
                    noWinner = false;
                }
            }
        }

        //4 vertical non-empty matches
        for(int i=0;i<=2;i++){
            for(int j=0;j<=6;j++){
                if(board[i][j] != 0 && board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j]){
                    noWinner = false;
                }
            }
        }

        // 4 diagonal matches, 2 loops because the diagonal can be in the direction of up-right or bottom-right (and their respective inverses)
        for(int i=5;i>=2;i--){
            for(int j=0;j<=3;j++){
                if(board[i][j] != 0 && board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && board[i][j] == board[i-3][j+3]){
                    noWinner = false;
                }
            }
        }
        for(int i=0;i<=2;i++){
            for(int j=0;j<=3;j++){
                if(board[i][j] != 0 && board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j] == board[i+3][j+3]){
                    noWinner = false;
                }
            }
        }

        if(turnCount == 42){
            noWinner = false;
            tie = true;
        }
    }

    public static void result(){
        if (tie) {
            System.out.println("There is a tie, neither player has won");
        } else {
            String winner;
            if (whosTurn == 0) {
                winner = "Red";
            } else {
                winner = "Yellow";
            }
            System.out.println("The " + winner + " player has won");
        }
    }
}
