package com.kthp.tro_choi_caro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Memento Pattern - Caretaker
 * Quản lý lịch sử các trạng thái game để hỗ trợ Undo/Redo
 * 
 * @author 2212391- Nguyễn Hoàng Nam Khánh
 */
public class MoveHistory {
    private List<GameStateMemento> history;
    private int currentIndex;
    
    public MoveHistory() {
        history = new ArrayList<>();
        currentIndex = -1;
    }
    
    /**
     * Lưu trạng thái mới vào lịch sử
     */
    public void saveState(GameStateMemento memento) {
        // Xóa tất cả các trạng thái sau currentIndex (nếu có)
        // Điều này xảy ra khi người dùng Undo rồi thực hiện nước đi mới
        while (history.size() > currentIndex + 1) {
            history.remove(history.size() - 1);
        }
        
        history.add(memento);
        currentIndex++;
    }
    
    /**
     * Undo - Quay lại trạng thái trước đó
     */
    public GameStateMemento undo() {
        if (canUndo()) {
            currentIndex--;
            return history.get(currentIndex);
        }
        return null;
    }
    
    /**
     * Redo - Tiến tới trạng thái tiếp theo
     */
    public GameStateMemento redo() {
        if (canRedo()) {
            currentIndex++;
            return history.get(currentIndex);
        }
        return null;
    }
    
    /**
     * Kiểm tra có thể Undo không
     */
    public boolean canUndo() {
        return currentIndex > 0;
    }
    
    /**
     * Kiểm tra có thể Redo không
     */
    public boolean canRedo() {
        return currentIndex < history.size() - 1;
    }
    
    /**
     * Xóa toàn bộ lịch sử
     */
    public void clear() {
        history.clear();
        currentIndex = -1;
    }
    
    /**
     * Lấy trạng thái hiện tại
     */
    public GameStateMemento getCurrentState() {
        if (currentIndex >= 0 && currentIndex < history.size()) {
            return history.get(currentIndex);
        }
        return null;
    }
    
    /**
     * Lấy số lượng trạng thái trong lịch sử
     */
    public int getHistorySize() {
        return history.size();
    }
}
