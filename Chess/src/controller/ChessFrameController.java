package controller;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Player;
import model.TableModel;
import view.ChessGameFrame;
import view.StartMenuFrame;

public class ChessFrameController {
	
	public static int[] selectButtonCoord(JButton[][] buttons, JButton selectedBtn) {
    	int[] output = new int[2];
    	for (int i = 0; i < buttons.length; i++)
			for (int j = 0; j < buttons[0].length; j++) {
				if (buttons[i][j] == selectedBtn) {
					output[0] = i;
					output[1] = j;
					break;
				}
			}
    	return output;
    }
    
	public static JButton[] swapBtns(JButton btn1, JButton btn2) {
    	JButton[] output = new JButton[2];
    	output[0] = btn1;
    	output[1] = btn2;
    	String auxString = output[0].getText();
		Color auxColor = output[0].getForeground();
		output[0].setText(output[1].getText());
		output[0].setForeground(output[1].getForeground());
		output[1].setText(auxString);
		output[1].setForeground(auxColor);
    	return output;
    }
    
	public static void makeMove(JButton btn1, JButton btn2, String blank) {
    	if (btn2.getText().equals(blank))
    		swapBtns(btn1, btn2);
    	else {
    		btn2.setText(btn1.getText());
    		btn2.setForeground(btn1.getForeground());
    		btn1.setText(blank);
    	}
    }
	
	public static String getCurrentPlayer(int playerTurn) {
		if (playerTurn % 2 == 0)
			return Player.PLAYER1.name();
		else return Player.PLAYER2.name();
	}
	
	public static String getCurrentPlayer(int playerTurn, String player1, String player2) {
		if (playerTurn % 2 == 0)
			return player1;
		else return player2;
	}
	
	public static void workInProgressInfo() {
        JOptionPane.showMessageDialog(null, "Work in progress, feature not available yet.", "Work in progress", JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static void endGame(String winPlayer, ChessGameFrame chessGameFrame, String frameName, String firstPlayerName, String secondPlayerName,
			int sizeX, int sizeY, TableModel table, CurrentTime time) {
		String text = winPlayer + " has won. Would you like to start another game?";
		Object[] options = {"Yes",
				"Return to main menu", 
                "Exit"};
		int choice = JOptionPane.showOptionDialog(chessGameFrame, 
				text,
				"Check mate Question",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, 
				options, 
				options[0]); 
		if (choice == JOptionPane.YES_OPTION) {
			TableModel newTable = new TableModel();
			@SuppressWarnings("unused")
			ChessGameFrame newChessGameFrame = new ChessGameFrame(frameName, firstPlayerName, secondPlayerName, sizeY, sizeY, newTable, 0, time);
			chessGameFrame.dispose();
		}
		else if(choice == JOptionPane.NO_OPTION) {
			@SuppressWarnings("unused")
			StartMenuFrame startMenuFrame = new StartMenuFrame(frameName, sizeX, sizeY, time);
			chessGameFrame.dispose();
		}
		else System.exit(0);
	}
}
