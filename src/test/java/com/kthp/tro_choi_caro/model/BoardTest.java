package com.kthp.tro_choi_caro.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void detectsAllFourWinningDirections() {
        assertWinningDirection(new int[][]{{3, 2}, {3, 3}, {3, 4}, {3, 5}, {3, 6}},
                WinningLine.Direction.HORIZONTAL);
        assertWinningDirection(new int[][]{{2, 3}, {3, 3}, {4, 3}, {5, 3}, {6, 3}},
                WinningLine.Direction.VERTICAL);
        assertWinningDirection(new int[][]{{2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}},
                WinningLine.Direction.DIAGONAL_MAIN);
        assertWinningDirection(new int[][]{{2, 6}, {3, 5}, {4, 4}, {5, 3}, {6, 2}},
                WinningLine.Direction.DIAGONAL_ANTI);
    }

    @Test
    void deepCopyDoesNotShareCellsWithOriginalBoard() {
        Board original = new Board();
        original.makeMove(4, 4, "X");

        Board copy = original.deepCopy();
        copy.undoMove(4, 4);
        copy.makeMove(5, 5, "O");

        assertEquals("X", original.getCell(4, 4).getContent());
        assertTrue(original.getCell(5, 5).isEmpty());
    }

    private void assertWinningDirection(int[][] positions, WinningLine.Direction direction) {
        Board board = new Board();
        for (int[] position : positions) {
            board.makeMove(position[0], position[1], "X");
        }

        int[] last = positions[positions.length - 1];
        WinningLine line = board.findWinningLine(last[0], last[1], "X");

        assertNotNull(line);
        assertEquals(direction, line.getDirection());
        assertEquals(5, line.size());
    }
}
