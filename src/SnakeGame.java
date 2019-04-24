import java.util.LinkedList;
import java.util.Queue;

public class SnakeGame {
    int xlen;
    int ylen;
    int[] head;
    int[] tail;
    int[][] board;
    int curfoodIndex;
    int[][] food;
    int foodnum;
    int curpoint;
    Queue<String> movehistory;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        //edge case: width or height =0??
        //food is null,{}, {{}...{}}
        this.board = new int[height][width];
        this.xlen = height;
        this.ylen = width;
        this.food = food;
        this.foodnum = food.length;
        curfoodIndex=0;
        if(curfoodIndex<foodnum){
            board[this.food[curfoodIndex][0]][this.food[curfoodIndex][1]]=-1;
        }
        this.board[0][0] = 1;
        this.head= new int[]{0,0};
        this.tail= new int[]{0,0};
        this.curpoint = 0;
        this.movehistory = new LinkedList<String>();
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        //calc next head
        this.movehistory.offer(direction);
        moveandupdate(direction,head);
        //valid date head
        if(!validateh(head,board)) return -1;
        //if hit food
        if(board[head[0]][head[1]]==-1){
            if(++this.curfoodIndex<foodnum){
                //set new food location
                board[this.food[curfoodIndex][0]][this.food[curfoodIndex][1]]=-1;
            }
            //update points
            this.curpoint++;
        }
        // normal move
        else{
            //reset board @pre tail to 0
            board[tail[0]][tail[1]]=0;
            String lm = this.movehistory.poll();
            //update tail location
            moveandupdate(lm,tail);
            if(board[head[0]][head[1]]==1) return -1;
        }
        //set board @ next head to 1 -- snake ocupied
        board[head[0]][head[1]] = 1;
        return curpoint;
    }
    private void moveandupdate(String direction, int[] pos){
        if(direction.equals("U")){
            pos[0]--;
        }
        else if(direction.equals("L")){
            pos[1]--;
        }
        else if(direction.equals("R")){
            pos[1]++;
        }
        else if(direction.equals("D")){
            pos[0]++;
        }
    }
    private boolean validateh(int[] head, int[][] board){
        if(head[0]<0||head[0]>=xlen||head[1]<0||head[1]>=ylen) return false;
        return true;
    }
    public static void main(String[] args){
        SnakeGame sk = new SnakeGame(3,3,new int[][]{{2,0},{0,0},{0,2},{2,2}});
        int res = sk.move("D");
        res = sk.move("D");
        res = sk.move("R");
        res = sk.move("U");
        res = sk.move("U");
        res = sk.move("L");
        res = sk.move("D");
        res = sk.move("R");
        res = sk.move("R");
        res = sk.move("U");
        res = sk.move("L");
        res = sk.move("D");
    }
}
