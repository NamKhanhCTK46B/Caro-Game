package com.kthp.tro_choi_caro.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    @Test
    void rejectsInvalidAndOccupiedMovesWithoutChangingTurn() {
        GameModel model = new GameModel();

        assertFalse(model.makeMove(-1, 0));
        assertEquals("X", model.getCurrentPlayer());
        assertEquals(0, model.getMoveNumber());

        assertTrue(model.makeMove(7, 7));
        assertEquals("O", model.getCurrentPlayer());
        assertFalse(model.makeMove(7, 7));
        assertEquals("O", model.getCurrentPlayer());
        assertEquals(1, model.getMoveNumber());
    }

    @Test
    void undoRedoAndNewMoveMaintainConsistentHistory() {
        GameModel model = new GameModel();
        assertTrue(model.makeMove(7, 7));
        assertTrue(model.makeMove(7, 8));

        assertTrue(model.undo());
        assertEquals("O", model.getCurrentPlayer());
        assertTrue(model.getBoard().isCellEmpty(7, 8));

        assertTrue(model.redo());
        assertEquals("X", model.getCurrentPlayer());
        assertEquals("O", model.getBoard().getCell(7, 8).getContent());

        assertTrue(model.undo());
        assertTrue(model.makeMove(8, 8));
        assertFalse(model.canRedo(), "Nước mới sau Undo phải xóa nhánh Redo cũ");
    }

    @Test
    void winningLineIsRestoredWithMemento() {
        GameModel model = createHorizontalWinForX();
        GameStateMemento winningState = model.createMemento();

        assertEquals(GameState.X_WON, model.getGameState());
        assertNotNull(model.getWinningLine());

        model.resetGame();
        assertNull(model.getWinningLine());

        model.restoreFromMemento(winningState);
        assertEquals(GameState.X_WON, model.getGameState());
        assertNotNull(model.getWinningLine());
        assertEquals(5, model.getWinningLine().size());
        assertEquals("X", model.getWinningLine().getWinner());
    }

    @Test
    void resetClearsBoardStateAndHistory() {
        GameModel model = new GameModel();
        model.makeMove(7, 7);
        model.makeMove(7, 8);

        model.resetGame();

        assertEquals(GameState.PLAYING, model.getGameState());
        assertEquals("X", model.getCurrentPlayer());
        assertEquals(0, model.getMoveNumber());
        assertTrue(model.getBoard().isCellEmpty(7, 7));
        assertFalse(model.canUndo());
        assertFalse(model.canRedo());
    }

    private GameModel createHorizontalWinForX() {
        GameModel model = new GameModel();
        int[][] moves = {
            {7, 3}, {0, 0},
            {7, 4}, {0, 2},
            {7, 5}, {0, 4},
            {7, 6}, {0, 6},
            {7, 7}
        };
        for (int[] move : moves) {
            assertTrue(model.makeMove(move[0], move[1]));
        }
        return model;
    }
}
