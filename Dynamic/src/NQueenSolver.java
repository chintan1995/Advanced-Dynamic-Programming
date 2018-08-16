import java.util.Scanner;

public class NQueenSolver 
{
	static int row=-1;
	public static void main(String [] args) throws Exception
	{
		int N;
		System.out.print("Enter the size of the board nxn: ");
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		sc.close();
		int board[][]=new int[N][N];
		
		boardFill(board,N,0);
			
		if(Nqueen(board,N,row))
		{
			System.out.println("Queens Solved");
			displayBoard(board,N);
		}
		else
			System.out.println("Solution NOT Possible");
		
	}
	
	static boolean Nqueen(int board[][],int N, int row)  //row is passed as parameter bc a global variable cannot be backtracked (inside the stack) for recursion
	{
		row++;
		
		if(row==N)
			return true;
		
		int col=0;
		while(col<N)
		{
			if(isSafe(board,N,row,col))
			{
				board[row][col]=1;
				displayBoard(board,N);
				if(Nqueen(board,N,row))
					return true;
				/*System.out.println("Now backtracking Step!");
				System.out.println("row= "+row+"col= "+col);*/
				board[row][col]=0;
				col++;
			}
			else
				col++;
		}
		return false;
	}
	
	static boolean isSafe(int board[][],int N, int i, int j)
	{
		for(int k=0; k<N; k++)
		{
			if(board[k][j]==1)return false;  //checks column
			
			if(board[i][k]==1)return false;  //checks row
		}
		
		
		//Now cross checks
		int k=i,l=j;
		while(k<N && l<N)  //both increase
		{
			if(board[k][l]==1)
				return false;
			k++; l++;
		}
		 k=i; l=j;
		while(k>=0 && l>=0)  //both decrease
		{
			if(board[k][l]==1)
				return false;
			k--; l--;
		}
		 k=i; l=j;
		while(k<N && l>=0)  //increase, decrease
		{
			if(board[k][l]==1)
				return false;
			k++; l--;
		}
		 k=i; l=j;
		while( k>=0 && l<N )  //decrease, increase
		{
			if(board[k][l]==1)
				return false;
			k--; l++;
		}

		return true;
	}
	
	
	static void displayBoard(int board[][],int N)
	{
		System.out.println();
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
				System.out.print(board[i][j]+" ");
			System.out.println();
		}
	}

	static void boardFill(int board[][],int N, int val)
	{
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
				board[i][j]=val;
		}
	}
	
}
