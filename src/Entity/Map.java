package Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    private static final int TILE_SIZE = 40;
    private static final int SCREEN_WIDTH = 15 * TILE_SIZE;
    private static final int SCREEN_HEIGHT = 15 * TILE_SIZE;

    private int data[][] = new int[SCREEN_HEIGHT / TILE_SIZE][SCREEN_WIDTH / TILE_SIZE];

    public Map() {
        try {
            File file = new File("./FileEksternal/map.txt");

            Scanner scan = new Scanner(file);

            int i = 0;
            while (scan.hasNextLine()) {
                String getDataString = scan.nextLine();
                for (int j = 0; j < getDataString.length(); j++) {
                    char c = getDataString.charAt(j);
                    data[i][j] = Character.getNumericValue(c);
                    // System.out.println(data[i][j]);
                }
                i += 1;
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Tidak Ditemukan");
        }
    }

    public int[][] get_landscape() {
        return this.data;
    }

    public boolean is_grassland(int i, int j) {
        return data[i][j] == 3;
    }

    public boolean is_mountain(int i, int j) {
        return data[i][j] == 1;
    }

    public boolean is_sea(int i, int j) {
        return data[i][j] == 2;
    }

    public boolean is_tundra(int i, int j) {
        return data[i][j] == 4;
    }

    public boolean is_grassland(Position p) {
        return data[p.get_x()][p.get_y()] == 3;
    }

    public boolean is_mountain(Position p) {
        return data[p.get_x()][p.get_y()] == 1;
    }

    public boolean is_sea(Position p) {
        return data[p.get_x()][p.get_y()] == 2;
    }

    public boolean is_tundra(Position p) {
        return data[p.get_x()][p.get_y()] == 3;
    }
}