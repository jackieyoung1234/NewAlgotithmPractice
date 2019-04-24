public class TicTacToe {
    int[][] board;//0-- ini val
    //1-- p1
    //2-- p2
    int N;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        N = n;
        board = new int[N][N];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        if(checkwin(player,row,col)) return player;
        return 0;
    }
    //true, a win
    private boolean checkwin(int pl, int row, int col){
        int i=0;
        for(;i<N;i++){
            if(board[row][i]!=pl)
                break;
        }
        if(i==N) return true;
        for(;i<N;i++){
            if(board[i][col]!=pl)
                break;
        }
        if(i==N) return true;
        if(row+col!=N-1&&row!=col){
            return false;
        }
        if(row==col){
            for(i=0;i<N;i++){
                if(board[i][i]!=pl){
                    break;
                }
            }
            if(i==N) return true;
        }
        if(row+col==N-1){
            for(i=0;i<N;i++){
                if(board[i][N-1-i]!=pl){
                    break;
                }
            }
            if(i==N) return true;
        }
        return false;
    }
    public static void main(String[] args){
        TicTacToe ttt = new TicTacToe(2);
        int res = 0;
        res = ttt.move(0,1,2);
        res = ttt.move(1,0,1);
    }
}
