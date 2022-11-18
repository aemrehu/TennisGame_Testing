import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}	
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();			
	}
	
	@Test
	public void testTennisGame_GetScoreReturnsCorrect_player1() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored(); //scores 1
		
		String score = game.getScore();
		assertEquals("Expected p1 scored once", "15 - love", score);
		
		game.player1Scored(); //scores 2.
		
		score = game.getScore();
		assertEquals("Expected p1 scored twice", "30 - love", score);
		
		game.player1Scored(); //scores 3.
		
		score = game.getScore();
		assertEquals("Expected p1 scored trice", "40 - love", score);
		
		game.player1Scored(); //scores 4.
		
		score = game.getScore();
		assertEquals("Expected p1 scored four times and wins", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_GetScoreReturnsCorrect_player2() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player2Scored(); //scores 1
		
		String score = game.getScore();
		assertEquals("Expected p2 scored once", "love - 15", score);
		
		game.player2Scored(); //scores 2.
		
		score = game.getScore();
		assertEquals("Expected p2 scored twice", "love - 30", score);
		
		game.player2Scored(); //scores 3.
		
		score = game.getScore();
		assertEquals("Expected p2 scored trice", "love - 40", score);
		
		game.player2Scored(); //scores 4.
		
		score = game.getScore();
		assertEquals("Expected p2 scored four times and wins", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_BothScoreSame() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();
		
		String score = game.getScore() ;
		assertEquals("15 - 15 score incorrect", "15 - 15", score);
		
		game.player1Scored();
		game.player2Scored();
		
		score = game.getScore() ;
		assertEquals("30 - 30 score incorrect", "30 - 30", score);
		
		game.player1Scored();
		game.player2Scored();
		
		score = game.getScore() ;
		assertEquals("40 - 40 score incorrect", "40 - 40", score);
		
	}
	
	@Test
	public void testTennisGame_PlayerXHasAdvantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		
		String score = game.getScore();		
		assertEquals("Expected Player1 to have advantage", "player1 has advantage", score);
		
		game.player2Scored();
		game.player2Scored();
		
		score = game.getScore();		
		assertEquals("Expected Player2 to have advantage", "player2 has advantage", score);
		
		TennisGame game2 = new TennisGame();
		
		game2.player2Scored();
		game2.player1Scored();
		game2.player2Scored();
		game2.player1Scored();
		game2.player2Scored();
		game2.player1Scored();
		game2.player2Scored();
		
		String score2 = game2.getScore();		
		assertEquals("Expected Player2 to have advantage", "player2 has advantage", score2);
		
		game2.player1Scored();
		game2.player1Scored();
		
		score2 = game2.getScore();		
		assertEquals("Expected Player1 to have advantage", "player1 has advantage", score2);
		
	}
	
	@Test
	public void testTennisGame_GetScoreReturns15301540() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		assertEquals("Expected 15-30","15 - 30",score);
		
		game.player2Scored();
		
		score = game.getScore();
		assertEquals("Expected 15-40","15 - 40",score);
	}
	
	@Test
	public void testTennisGame_GetScoreReturns30154015() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Expected 30-15","30 - 15",score);
		
		game.player1Scored();
		
		score = game.getScore();
		assertEquals("Expected 40-15","40 - 15",score);
	}
	
	@Test
	public void testTennisGame_P1WinsWith2Points() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Expected p1 to win with 2 points","player1 wins",score);
	}
	
	@Test
	public void testTennisGame_P2WinsWith2Points() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		assertEquals("Expected p2 to win with 2 points","player2 wins",score);
	}
	
	
	
}
