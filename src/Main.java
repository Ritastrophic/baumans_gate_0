import java.util.*;
import java.util.Random;

public char check_answer() {
    Scanner ans = new Scanner(System.in);
    char answer = ans.next().charAt(0);
    return answer;}

public void main(String[] args) {
    System.out.println("Нажмите 1, чтобы начать новую игру");
    if (check_answer() == '1') {
        Game new_game = new Game(100, false );
        new_game.gameBegin();
        ArrayList<Unit> remaining_characters = new ArrayList<Unit>();
        ArrayList<Unit> enemy_remaining_characters = new ArrayList<Unit>();
        Set<Integer> remaining_count = new HashSet<Integer>();
        Set<Integer> remaining_enemy_count = new HashSet<Integer>();
        new_game.buying(new_game.getMoney(), remaining_characters, enemy_remaining_characters, remaining_count, remaining_enemy_count);
        int new_size = remaining_characters.size();
        new_game.showUnits(remaining_characters, enemy_remaining_characters);
        Field new_field = new Field(15,15);
        new_field.setField( remaining_characters, enemy_remaining_characters );
        new_game.game( remaining_characters, enemy_remaining_characters, new_size, new_field, remaining_count, remaining_enemy_count); //заканчивается геймовером
    }
    else {
        System.out.println("Вы выбрали не начинать игру.");
    }
}