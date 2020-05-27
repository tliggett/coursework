package homework4;

public class Demo {
	//Desc:	The network is showing a game at 13:15.  The length of the game is 3:23.  News starts at 
	//	17:00.  After the game, the network wants to conduct post-game interviews.  The 
	//	program determines how much time for the interview.
	//Output:The time when the game begins and ends and the length of post-game interviews printed 
	//	on the screen
	public static void main(String[] args)
	{
			
		Time24 startGame = new Time24(13,15);
		Time24 endGame = new Time24();
		endGame.addTime(startGame.getHour()*60 + startGame.getMinute() + 203);
		Time24 timeForInterviews = endGame.interval(new Time24(17, 0));
		System.out.println("" + (char)7);



		System.out.println("The game begins at " + startGame);
		System.out.println("The game ends at " + endGame);
		System.out.println("Post game interviews last " + timeForInterviews);
	}

}
