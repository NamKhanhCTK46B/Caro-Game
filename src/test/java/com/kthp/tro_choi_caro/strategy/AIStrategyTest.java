package com.kthp.tro_choi_caro.strategy;

import com.kthp.tro_choi_caro.model.Board;
import com.kthp.tro_choi_caro.model.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIStrategyTest {

    @Test
    void mediumAiWinsInsteadOfBlockingWhenBothPlayersThreaten() {
        Board board = new Board();
        placeLine(board, 5, 1, "O");
        placeLine(board, 8, 1, "X");

        Move move = new MediumAIStrategy().findBestMove(board, "O");

        assertNotNull(move);
        board.makeMove(move.getRow(), move.getCol(), "O");
        assertTrue(board.checkWinFromPosition(move.getRow(), move.getCol(), "O"));
    }

    @Test
    void mediumAiBlocksAnImmediateLoss() {
        Board board = new Board();
        placeLine(board, 8, 1, "X");

        Move move = new MediumAIStrategy().findBestMove(board, "O");

        assertNotNull(move);
        assertEquals(8, move.getRow());
        assertTrue(move.getCol() == 0 || move.getCol() == 5);
    }

    @Test
    void hardAiTakesAnImmediateWinningMove() {
        Board board = new Board();
        placeLine(board, 5, 1, "O");

        Move move = new HardAIStrategy().findBestMove(board, "O");

        assertNotNull(move);
        board.makeMove(move.getRow(), move.getCol(), "O");
        assertTrue(board.checkWinFromPosition(move.getRow(), move.getCol(), "O"));
    }

    @Test
    void aiSearchDoesNotMutateTheGivenBoard() {
        Board board = new Board();
        board.makeMove(7, 7, "X");

        new MediumAIStrategy().findBestMove(board, "O");
        new HardAIStrategy().findBestMove(board, "O");

        assertEquals("X", board.getCell(7, 7).getContent());
        assertEquals(Board.BOARD_SIZE * Board.BOARD_SIZE - 1, board.getEmptyCells().size());
    }

    private void placeLine(Board board, int row, int startCol, String player) {
        for (int col = startCol; col < startCol + 4; col++) {
            board.makeMove(row, col, player);
        }
    }
}
