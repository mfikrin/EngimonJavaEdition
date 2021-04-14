package src.GameState;

import java.util.Stack;

public class GameState{
    private static Stack<GameState> GameState_history = new Stack<GameState>();
    private static GameState current_GameState;
    
    public GameState(){
        // ... Terusin disini gais, speknya liat di notul ya (https://tiny.cc/NotulTubesPBO)
        
        current_GameState = this;
    }


    // GETTER
    public Stack<GameState> get_GameState_history(){
        return GameState_history;
    }

    public GameState get_current_GameState(){
        return current_GameState;
    }

    // Other Methods
    public void update(){
        GameState_history.push(current_GameState);
        new GameState();
        // ...
    }


}