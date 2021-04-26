package src.GameState;

import Entity.Engimon.Engimon;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import src.Entity.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class GameState{
    @SerializedName(value = "History GameState", alternate = "GameState_history")
    private static Stack<GameState> GameState_history = new Stack<GameState>();
    @SerializedName(value = "Current GameState", alternate = "current_GameState")
    private static GameState current_GameState;
    @SerializedName(value = "Player",alternate = "player")
    private Player player;
    @SerializedName(value = "Engimon Liar",alternate = "list_engimon_liar")
    private ArrayList<Engimon> list_engimon_liar;
    @SerializedName("isBreeding")
    private Boolean isBreeding;
    @SerializedName("isBattle")
    private Boolean isBattle;
    @SerializedName("isLearn")
    private Boolean isLearn;


    public GameState(){
        // ... Terusin disini gais, speknya liat di notul ya (https://tiny.cc/NotulTubesPBO)
        player = new Player();
        list_engimon_liar = new ArrayList<Engimon>();
        isBreeding = false;
        isBattle = false;
        isLearn = false;
        current_GameState = this;
        GameState_history.push(current_GameState);
    }

    public GameState(Player players,ArrayList<Engimon> list_engimon_liars,Boolean isBreedings, Boolean isBattles, Boolean isLearns){
        player = players;
        list_engimon_liar = list_engimon_liars;
        isBreeding = isBreedings;
        isBattle = isBattles;
        isLearn = isLearns;
        current_GameState = this;
        GameState_history.push(current_GameState);
    }


    // GETTER
    public Stack<GameState> get_GameState_history(){
        return GameState_history;
    }

    public void update_gamestate(String statePath) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("GameState.txt"))
        GameState g = (GameState) in.readObject();
        current_GameState = g;
    }

    public void save_gameState(String statepath){
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GameState.txt"))
        out.writeObject(current_GameState)
    }

    public GameState get_current_GameState(){
        return current_GameState;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Engimon> getList_engimon_liar() {
        return list_engimon_liar;
    }

    public Boolean getBattle() {
        return isBattle;
    }

    public Boolean getBreeding() {
        return isBreeding;
    }

    public Boolean getLearn() {
        return isLearn;
    }

    // SETTER
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setList_engimon_liar(ArrayList<Engimon> list_engimon_liar) {
        this.list_engimon_liar = list_engimon_liar;
    }

    public void setIsBattle(Boolean battle) {
        isBattle = battle;
    }

    public void setIsBreeding(Boolean breeding) {
        isBreeding = breeding;
    }
    public void setIsLearn(Boolean learn) {
        isLearn = learn;
    }



    // Other Methods
    public void update_gamestate(String statePath) throws IOException {
        GameState_history.push(current_GameState);

        Gson gson = new Gson();

//        String statePath = String.format("D:\\JURUSAN_IF\\Sem 4\\OOP\\tubes2\\EngimonJavaEdition\\src\\GameState/GameState.json");

        String state = new String(Files.readAllBytes(Paths.get(statePath)));
        System.out.println(state);
        GameState newGameState = gson.fromJson(state, GameState.class);
        System.out.println(newGameState.getList_engimon_liar());
        System.out.println(newGameState.getPlayer().get_pos().get_y());
        newGameState.show_gamestate();

        current_GameState = newGameState;
    }

    public void show_gamestate(){
        player.show_player();
        System.out.println(getList_engimon_liar());
        System.out.println(getBreeding());
        System.out.println(getLearn());
        System.out.println(getBattle());
    }

    public void save_gameState(String statepath){
        Gson gson = new Gson();

        String json = gson.toJson(current_GameState);

        System.out.println(json);

//        "D:\\JURUSAN_IF\\Sem 4\\OOP\\tubes2\\EngimonJavaEdition\\src\\GameState/GameState.json"
        try (FileWriter file = new FileWriter(statepath)) {
            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}