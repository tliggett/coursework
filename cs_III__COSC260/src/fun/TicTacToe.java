package fun;

import java.util.LinkedList;
 
public class TicTacToe {
	private static LinkedList<String> games;
	private static int length;
	
	//markers
	private static int logic;
	private static int w;
	private static int l;
	private static int c;
	private static int t;
	
	public static void main(String[] args) 
	{
		games = new LinkedList<String>();
		length = 50000;
		logic = 0;
		for(int i = 0; i < length; i++)
		{
			if(i%2500 == 0) 
			{
			System.out.println("Checkpoint..." + i);
			System.out.println("\tWins: " + w);
			System.out.println("\tLosses: " + l);
			System.out.println("\tCats: " + c);
			System.out.println("\tTotal: " + t);
			System.out.println("\tLogical Moves: " + logic);
			w = l = c = t = l = 0;
			}
			String game = "";
			if(i%2 == 0) game += "a";
			else game += "r";
			games.add(playGame(game));
			
		}
		System.out.println("Finished!");
		System.out.println("\tWins: " + w);
		System.out.println("\tLosses: " + l);
		System.out.println("\tCats: " + c);
		System.out.println("\tTotal: " + t);
		System.out.println("\tLogical Moves: " + logic);
		
		
		
		
	}
	
	public static String playGame(String game)
	{
		boolean aTurn = false;
		if(game.contains("a")) aTurn = true;
		String playerA = "";
		String playerR = "";
		String pointer = "";
		
		do
		{
			
			if(aTurn)
			{
				
				pointer = compDecision(game);
				playerA += "" + pointer;
				game += "" + pointer;
			}
			else
			{
				do
				{	
					pointer = "" + (int) (Math.random()*9);
				} while(game.contains("" + pointer)); 
				playerR += "" + pointer;
				game += "" + pointer;
			}
			
			
			aTurn = !aTurn;
			
			
			if(hasWon(playerA))
			{
				
				game += "w";
				w++;
				break;
			}
			
			if(hasWon(playerR))
			{
				
				game += "l";
				l++;
				break;
			}
			
			if(game.length() >= 10)
			{
				
				game += "c";
				c++;
				break;
			}
			
		}while(true);
		//System.out.println(game);
		return game;
	}
	
	public static String compDecision(String g)
	{
		LinkedList<String> outcomes = new LinkedList<String>();
		for(String game : games)
			if(game.contains(g)) outcomes.add(game.replace(g, ""));
		double percent = 0;
		String ret = "x";
		double wins = 0;
		double total = 0;
		for(int i = 0; i<9; i++)
		{	
			if(!g.contains("" + i))
			
			{
			for(String o : outcomes)
			{
				if(o.charAt(0) == '0' + i)
				{
					total ++;
					if(o.contains("w")) wins+=1;
					//if(o.contains("c")) wins += 0.5;
				}
			}
			if(wins/total > percent && wins/total > .50)
			{
				percent = wins/total;
				ret = "" + i;
			}
			wins = 0;
			total = 0;
			}
		}
			
			if(ret.equals("x"))
				{
					int i = (int) (Math.random()*9);
					while(g.contains("" + i)) i = (int) (Math.random()*9);
					ret = "" + i;
				}
				else
				{
					logic++;
					//System.out.println("Logic!");
				}
		return ret;
	}
	
	
	
	public static boolean hasWon(String player)
	{
		if(threeInARow(player, 0,1,2)) return true;
		if(threeInARow(player, 3,4,5)) return true;
		if(threeInARow(player, 6,7,8)) return true;
		if(threeInARow(player, 0,3,6)) return true;
		if(threeInARow(player, 1,4,7)) return true;
		if(threeInARow(player, 2,5,8)) return true;
		if(threeInARow(player, 0,4,8)) return true;
		if(threeInARow(player, 2,4,6)) return true;
		return false;
	}
	
	public static boolean threeInARow(String player, int un, int deux, int trois)
	{
		if(player.contains("" + un) && player.contains("" + deux) && player.contains("" + trois)) return true;
		return false;
	}

}
