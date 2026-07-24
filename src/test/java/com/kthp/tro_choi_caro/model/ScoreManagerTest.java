package com.kthp.tro_choi_caro.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreManagerTest {

    private ScoreManager scores;

    @BeforeEach
    void resetScores() {
        scores = ScoreManager.getInstance();
        scores.reset();
    }

    @Test
    void recordsWinsDrawsAndDetailedStatistics() {
        scores.addWin("X");
        scores.addWin("O");
        scores.addDraw();

        assertEquals(4, scores.getPlayerScore());
        assertEquals(4, scores.getAiScore());
        assertEquals(1, scores.getDrawCount());
        assertEquals(3, scores.getTotalGames());
        assertTrue(scores.getDetailedStats().contains("Bạn: 4 điểm (1 thắng)"));
        assertTrue(scores.getDetailedStats().contains("AI: 4 điểm (1 thắng)"));
    }
}
