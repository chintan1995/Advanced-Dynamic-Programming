import java.io.*;

/**
 * 
 * @author Chintan
 * Input the inital sudoku board in the file named "sudoku.txt".
 * Blank boxes should be represented by zero.
 * A sample sudoku.txt is already given.
 */

public class SudokuSolver
{
	public static void main(String [] args) throws Exception
	{
		int board[][]=new int[9][9];
		
		try {
			fillBoard(board);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		
		System.out.println();
		
		if(sudokuGame(board))
		{
			clearScreen();  //This function will only work properly in vanilla java execution(cmd prompt execution??)
							//It will not work correctly in IDEs
							//Anyhow, it will NOT give any error in execution :)
			
			System.out.println("\nSudoku Solved!");
			displayBoard(board);
		}
		else
			System.out.println("\nNo solution found!");
			
	}
	
	static void fillBoard(int board[][]) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("sudoku.txt"));
		String line;
		String s_value[];
		
		line=br.readLine();
		
		int i=0;
		while(line!=null) 
		{
			s_value=line.split(" ");
			
			for(int j=0; j<s_value.length; j++)
			{
				board[i][j]=Integer.parseInt(s_value[j]);
			}
			i++;
			line=br.readLine();
		}
		br.close();
	}
	
	static boolean sudokuGame(int board[][])
	{
		int i,j;
		
		if(isFull(board))  //base condition for recursion
		{
			return true;
		}
		clearScreen();
		System.out.println();
		displayBoard(board);
		int position[]=new int[2];
		position=place(board);  //checks for the vacant place on the board
		i=position[0];
		j=position[1];
		
		int possible_values[]=new int[10];
		possible_values=possibilities(board, i, j);  //possibilities() method checks which values are possible at the position i,j
		
		for(int k=1; k<=9; k++)
		{
			if(possible_values[k]==1)
			{
				board[i][j]=k;
				if(sudokuGame(board))
					return true;
				board[i][j]=0;
			}
		}
		
		return false;
	}
	
	static int[] place(int board[][])
	{
		int pos[]=new int[2];
		
		outerloop:
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
			{
				if(board[i][j]==0)
				{
					pos[0]=i;
					pos[1]=j;
					break outerloop;
				}
			}
		return pos;
	}
	
	static int[] possibilities(int board[][], int i, int j)  //i,j are the position at which we want to check the possibilities
	{
		int l, k, x; 
		
		int possible[]=new int[10];  //possible numbers from 1 to 9
		java.util.Arrays.fill(possible,1); 
		
		//will check for the full row and column
		for(k=0,l=0; k<9; k++,l++)
		{
			if(board[k][j]>0) //checks for rows
			{
				x=board[k][j];
				possible[x]=0; // 0 means, that particular value is not possible
			}
			if(board[i][l]>0) //checks for column
			{
				x=board[i][l];
				possible[x]=0;
			}
		}
		
		//now, will check for the sub block 3x3
		
		//reusing k,l as k=row, l=column
		k= i - (i%3);
		l= j - (j%3);
		int init_sub_row=k;
		int init_sub_col=l;
		
		//now we are the at initial cell of the sub block, pointed by k,l
		int k_limit=k+3;
		int l_limit=l+3;  //these two st.=> bounding the sub block
		
		for(k= init_sub_row ; k < k_limit; k++)
			for(l= init_sub_col ; l < l_limit; l++)
			{
				if(board[k][l]>0)
				{
					x=board[k][l];
					possible[x]=0;
				}
			}
		
		return possible;
	}
	
	static boolean isFull(int board[][])
	{
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				if(board[i][j]==0)
				{
					return false;
				}
			}
		}
		return true; // board is full
	}
	
	static void displayBoard(int board[][])
	{
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
   }  
}
