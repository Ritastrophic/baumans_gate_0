import java.util.*;

public class Game {

    private int money;
    private boolean gameOver;

    public Game(int money, boolean gameOver) {
        this.money = money;
        this.gameOver = gameOver;
    }
    public int getMoney() { return money; }
    public boolean getGameOver() { return gameOver; }
    public void setGameOver(boolean z) { this.gameOver = z;}

    public static Unit create_unit(int unit_num) {
        switch (unit_num) {
            case (1): {
                Pedestrian swordsman = new Pedestrian("Мечник", 50, 5, 1, 8, 3, 3.0 ,10, '*', '*');
                return swordsman;
            }
            case (2): {
                Pedestrian spearmаn = new Pedestrian("Копьеносец", 35, 3, 1, 4, 6, 6.0,15, '*', '*');
                return spearmаn;
            }
            case (3): {
                Pedestrian axeman = new Pedestrian("Топорщик", 45, 9, 1, 3, 4,4.0, 20, '*', '*');
                return axeman;
            }
            case (4): {
                Archer archer_long = new Archer("Лучник(дл. лук)", 30, 6, 5, 8, 2, 2.0,15, '*', '*');
                return archer_long;
            }
            case (5): {
                Archer archer_short = new Archer("Лучник(кор. лук)", 25, 3, 3, 4, 4,4.0, 19, '*', '*');
                return archer_short;
            }
            case (6): {
                Archer crossbowman = new Archer("Арбалетчик", 40, 7, 6, 3, 2, 2.0,23, '*', '*');
                return crossbowman;
            }
            case (7): {
                Horseman knight = new Horseman("Рыцарь", 30, 5, 1, 3, 6, 6.0, 20, '*', '*');
                return knight;
            }
            case (8): {
                Horseman cuirassier = new Horseman("Кирасир", 50, 2, 1, 7, 5, 5.0, 23, '*', '*');
                return cuirassier;
            }
            case (9): {
                Horseman horse_archer = new Horseman("Конный лучник", 25, 3, 3, 2, 5, 5.0, 25, '*', '*');
                return horse_archer;
            }
            default:
                Unit nil = new Unit("0", 0, 0, 0, 0, 0, 0.0,0, ' ', ' ');
                return nil;
        }
    }
    public static void buying(int money, ArrayList<Unit> characters, ArrayList<Unit> enemy_characters, Set<Integer> remaining_count, Set<Integer> remaining_enemy_count) {
        int k = 0;
        Unit character;
        Unit enemy_character;
        while( money >= 10 ) {
            Scanner un = new Scanner(System.in);
            char unit_num = un.next().charAt(0);
            if ( unit_num == '0') {
                break;
            } else if ((unit_num-'0') > 9 || !Character.isDigit(unit_num)) {
                System.out.println("Ошибка. Попробуйте еще раз");
                unit_num = 0;
            }
            else {
                character = create_unit(unit_num-'0');
                int char_cost = character.getCost();
                if (money < char_cost) {System.out.println("У вас не хватает монет для покупки юнита"); }
                else {
                    Random rn = new Random();
                    int rand = rn.nextInt(9) + 1;
                    money -= char_cost;
                    characters.add(character);
                    k++;
                    remaining_count.add(k);
                    remaining_enemy_count.add(k);
                    System.out.println(character.getName());
                    enemy_character = create_unit(rand);
                    enemy_characters.add(enemy_character);
                }
                System.out.println("У вас осталось " + money + " монет");

            }
        }
    }
    public void gameBegin() {
        System.out.println("На город готовится нападение! В городской казне есть " + this.money +
                " монет. Вам предлагается собрать армию из юнитов и спасти город! \nВ городе " +
                "есть юниты со следующими характеристиками:");
        showCharacteristics();
        System.out.println("\nВыберите номер юнита, чтобы его купить. Чтобы выйти из магазина, нажмите 0");
    }
    public void showCharacteristics(){
        System.out.format("+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "| Номер |   Название    | Здоровье | Атака | Дальность атаки | Защита | Перемещение | Стоимость |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   1   |     Мечник    |    50    |   5   |        1        |    8   |      3      |     10    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   2   |   Копьеносец  |    35    |   3   |        1        |    4   |      6      |     15    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   3   |    Топорщик   |    45    |   9   |        1        |    3   |      4      |     20    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   4   |Лучник(дл.лук) |    30    |   6   |        5        |    8   |      2      |     15    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   5   |Лучник(кор.лук)|    25    |   3   |        3        |    4   |      4      |     19    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   6   |  Арбалетчик   |    40    |   7   |        6        |    3   |      2      |     23    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   7   |    Рыцарь     |    30    |   5   |        1        |    3   |      6      |     20    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   8   |    Кирасир    |    50    |   2   |        1        |    7   |      5      |     23    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n" +
                "|   9   | Конный лучник |    25    |   3   |        3        |    2   |      5      |     25    |%n" +
                "+-------+---------------+----------+-------+-----------------+--------+-------------+-----------+%n");
    }
    public void showUnits(ArrayList<Unit> remaining_characters, ArrayList<Unit> enemy_remaining_characters) {
        System.out.println("\nСписок купленных юнитов:");
        String temp;
        int k = remaining_characters.size();
        for (int i=1;i<k+1;i++){
            temp = (remaining_characters.get(i-1)).getName();
            System.out.println(i + " | " + temp + " | здоровье: " + (remaining_characters.get(i-1)).getHealth());
        }
        k = enemy_remaining_characters.size();
        System.out.println("Список юнитов противника:");
        char ch = 'a';
        for (int i=1;i<k+1;i++){
            temp = (enemy_remaining_characters.get(i-1)).getName();
            System.out.println(ch + " | " + temp + " | здоровье: " + (enemy_remaining_characters.get(i-1)).getHealth());
            ch+=1;
        }
    }
    public void showOptions(ArrayList<Unit> remaining_characters, ArrayList<Unit> remaining_enemy_characters) {
        System.out.println("\n\n1 - показать список оставшихся юнитов\n2 - показать характеристики всех юнитов\n" +
                "3 - показать обозначения на карте и эффекты клеток на юнитов\n4 - выйти из игры\nНажмите любую другую клавишу, чтобы выйти из меню опций");
        Scanner scanner = new Scanner(System.in);
        char choice = scanner.next().charAt(0);
        switch (choice) {
            case ('1'): {
                this.showUnits(remaining_characters, remaining_enemy_characters);
                this.showOptions(remaining_characters, remaining_enemy_characters);
                break;
            }
            case('2'): {
                this.showCharacteristics();
                this.showOptions(remaining_characters, remaining_enemy_characters);
                break;
            }
            case ('3'): {
                System.out.println("\nОбозначения на карте:\n" +
                        "* - Oбычная клетка\n" +
                        "# - Болото\n" +
                        "@ - Холм\n" +
                        "! – Дерево\n" +
                        "1, 2, 3 – Ваши юниты\n" +
                        "a, b, c – Юниты противника\n")    ;
                System.out.format("+-------+---------------+--------+------+-----+%n" +
                        "| Номер |   Название    | Болото | Холм | Лес |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   1   |     Мечник    |   1.5  |  2.0 | 1.2 |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   2   |   Копьеносец  |   1.5  |  2.0 | 1.2 |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   3   |    Топорщик   |   1.5  |  2.0 | 1.2 |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   4   |Лучник(дл.лук) |   1.8  |  2.2 | 1.0 |%n"+
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   5   |Лучник(кор.лук)|   1.8  |  2.2 | 1.0 |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   6   |  Арбалетчик   |   1.8  |  2.2 | 1.0 |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   7   |    Рыцарь     |   2.2  |  1.2 | 1.5 |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   8   |    Кирасир    |   2.2  |  1.2 | 1.5 |%n" +
                        "+-------+---------------+--------+------+-----+%n" +
                        "|   9   | Конный лучник |   2.2  |  1.2 | 1.5 |%n" +
                        "+-------+---------------+--------+------+-----+%n");
                this.showOptions(remaining_characters, remaining_enemy_characters);
                break;
            }
            case('4'): {
                System.out.println("Вы выбрали закончить игру сейчас.");
                this.setGameOver(true);
                System.exit(0);
            }
            default: {
                System.out.println("Вы выбрали выход из меню опций.");
                break;
            }
        }
        System.out.println();
    }
    public void game(ArrayList<Unit> remaining_chars, ArrayList<Unit> remaining_enemy_chars,int start_size, Field game_field, Set<Integer> remaining_count, Set<Integer> remaining_enemy_count ) {
        while (!this.getGameOver()) {
            player_step(remaining_chars, remaining_enemy_chars, start_size, game_field, remaining_count, remaining_enemy_count);
            if (this.getGameOver()) {
                System.out.println("Игра окончена.");
            }
            else {
                if (remaining_enemy_count.isEmpty()) {
                    System.out.println("Вы победили!");
                    this.setGameOver(true);
                    break;
                }
                enemy_step(remaining_chars, remaining_enemy_chars, game_field, remaining_count, remaining_enemy_count);
                if (remaining_count.isEmpty()) {
                    System.out.println("Вы проиграли.");
                    this.setGameOver(true);
                    break;
                }
            }
        }
    }
    public void player_step(ArrayList<Unit> remaining_characters, ArrayList<Unit> enemy_remaining_characters, int start_size, Field game_field, Set<Integer> remaining_count, Set<Integer> remaining_enemy_count) {
        System.out.println("\nВаш ход");
        Unit unit;
        char number_char;
        Scanner scanner;
        Set<Integer> used_for_attack = new HashSet<>();
        Set<Integer> used_for_move = new HashSet<>();
        System.out.println("\nПомните, что каждый юнит " +
                "можно использовать по одному разу для атаки или перемещения. Используйте это с умом. " );
        for (int i=0; i<remaining_count.size();i++) {
            System.out.println("\nВыберите номер юнита, которым хотите управлять или " +
                    "нажмите 0, чтобы просмотреть меню опций. ");
            scanner = new Scanner(System.in);
            number_char = scanner.next().charAt(0);
            if (!Character.isDigit(number_char) || number_char - '0' < 0 || number_char - '0' > start_size) {
                System.out.println("Ошибка. У Вас нет юнита с таким номером. \nПопробуйте еще раз.");
                i--;
            } else {
                if (number_char - '0' == 0) {
                    this.showOptions(remaining_characters, enemy_remaining_characters);
                    i--;
                } else {
                    unit = remaining_characters.get((number_char - '0') - 1);
                    if (unit.getHealth()<=0) {
                        System.out.println("Юнит мертв, им нельзя управлять.");
                        i--;
                    }
                    else {
                        System.out.println("здоровье: " + unit.getHealth() + " | защита: " + unit.getShield() + " | дальность атаки: "+ unit.getAttack_range() + " | перемещение: " + unit.getMoving_range());
                        System.out.println("Вы хотите переместить юнит " + unit.getName() + " или атаковать им противника? \n1 - переместить, 2 - атаковать.\n" +
                                "Нажмите 0, чтобы просмотреть меню опций.");
                        switch (check_answer()) {
                            case ('0'): {
                                this.showOptions(remaining_characters, enemy_remaining_characters);
                                i--;
                                break;
                            }
                            case ('1'): {
                                if (used_for_move.contains(number_char - '0')) {
                                    System.out.println("Вы уже перемещали этот юнит в течение этого хода.");
                                } else {
                                    game_field.showField();
                                    moving(unit, game_field);
                                    used_for_move.add(number_char - '0');
                                }
                                System.out.println("Вы хотите атаковать противника этим юнитом? Нажмите 1, если да, и любую другую клавишу, если нет.");
                                if (check_answer() == '1') {
                                    if (used_for_attack.contains(number_char - '0')) {
                                        System.out.println("Вы уже вы уже атаковали противника этим юнитом в течение этого хода.");
                                    } else {
                                        System.out.println("ATTACK!!!!");
                                        attacking(unit, enemy_remaining_characters, remaining_enemy_count);
                                        used_for_attack.add(number_char - '0');
                                        game_field.showField();
                                    }
                                }
                                break;
                            }
                            case ('2'): {
                                if (used_for_attack.contains(number_char - '0')) {
                                    System.out.println("Вы уже вы уже атаковали противника этим юнитом в течение этого хода.");
                                } else {
                                    System.out.println("ATTACK!!!!");
                                    attacking(unit, enemy_remaining_characters, remaining_enemy_count);
                                    used_for_attack.add(number_char - '0');
                                    game_field.showField();
                                }
                                System.out.println("Вы хотите переместить юнит? Нажмите 1, если да, и любую другую клавишу, если нет.");
                                if (check_answer() == '1') {
                                    if (used_for_move.contains(number_char - '0')) {
                                        System.out.println("Вы уже перемещали этот юнит в течение этого хода.");
                                    } else {
                                        moving(unit, game_field);
                                        used_for_move.add(number_char - '0');
                                    }
                                }
                                break;
                            }
                            default: {
                                System.out.println("\nВы нажали что-то не то.\n");
                                i--;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public void enemy_step(ArrayList<Unit> remaining_chars, ArrayList<Unit> remaining_enemy_chars, Field game_field, Set<Integer> remaining_count, Set<Integer> remaining_enemy_count) {
        System.out.println("\nХод противника");
        Unit unit;
        Unit good_unit;
        Unit target_unit;
        double distance = 100;
        for (int i = 0; i < remaining_enemy_count.size(); i++) {
            boolean not_attacked = true;
            unit = remaining_enemy_chars.get(i);
            if (unit.getHealth()>0) {
                int unit_in_set = 0;
                target_unit = unit; // по умолчанию
                //looking for the nearest enemy
                double min_range = 1000;
                for (int k = 0; k < remaining_count.size(); k++) {
                    good_unit = remaining_chars.get(k);
                    distance = Math.pow(Math.pow((unit.getPositionx() - good_unit.getPositionx()), 2) + Math.pow((unit.getPositiony() - good_unit.getPositiony()), 2), 0.5);
                    if (distance <= min_range && good_unit.getHealth()>0) {
                        min_range = distance;
                        target_unit = good_unit; //found it
                        unit_in_set = k;
                    }
                }
                if (distance <= unit.getAttack_range()) {
                    unit.attack(target_unit); //if you can attack, you attack
                    not_attacked = false;
                }
                else {
                    unit.setAvailable_moving_range(unit.getMoving_range());
                    while (unit.getAvailable_moving_range() >= 1 && not_attacked) {
                        if (target_unit.getPositionx() < unit.getPositionx() && check_if_free_and_safe(unit, 'a', game_field)) {
                            unit.move('a');
                            unit.setAvailable_moving_range(unit.fine_for_obstacles(unit.getPositionx(), unit.getPositiony(), game_field));
                            game_field.update_field(unit);
                        } else if (target_unit.getPositionx() > unit.getPositionx() && check_if_free_and_safe(unit, 'd', game_field)) {
                            unit.move('d');
                            unit.setAvailable_moving_range(unit.fine_for_obstacles(unit.getPositionx(), unit.getPositiony(), game_field));
                            game_field.update_field(unit);
                        } else if (target_unit.getPositiony() < unit.getPositiony() && check_if_free_and_safe(unit, 'w', game_field)) {
                            unit.move('w');
                            unit.setAvailable_moving_range(unit.fine_for_obstacles(unit.getPositionx(), unit.getPositiony(), game_field));
                            game_field.update_field(unit);
                        } else if (check_if_free_and_safe(unit, 's', game_field)) {
                            unit.move('s');
                            unit.setAvailable_moving_range(unit.fine_for_obstacles(unit.getPositionx(), unit.getPositiony(), game_field));
                            game_field.update_field(unit);
                        }
                        else {
                            unit.setAvailable_moving_range(0.0);
                        }
                        distance = Math.pow(Math.pow((unit.getPositionx() - target_unit.getPositionx()), 2) + Math.pow((unit.getPositiony() - target_unit.getPositiony()), 2), 0.5);
                        if (distance <= unit.getAttack_range()) {
                            unit.attack(target_unit);
                            not_attacked = false;
                        }
                    }
                }
                if (not_attacked) {
                    System.out.println("Враг " + unit.getName() + " не успел атаковать вас.");
                }
                else {
                    System.out.println("У вашего юнита " + target_unit.getName() + " осталось " + target_unit.getHealth() + " единиц здоровья");
                }
                if (target_unit.getHealth() <= 0) {
                    System.out.println("Ваш юнит был убит противником.");
                    System.out.println(remaining_count.size());
                    remaining_count.remove(unit_in_set);
                    System.out.println(remaining_count.size());
                }
            }
            else {
                System.out.println("Юнит мертв.");
            }
        }
    }
    public char check_answer() {
        Scanner ans = new Scanner(System.in);
        char answer = ans.next().charAt(0);
        return answer;
    }
    public boolean check_if_free_and_safe(Unit unit, char mov, Field game_field) {
        boolean check = false;
        int x = unit.getPositionx();
        int y = unit.getPositiony();
        char place;
        switch (mov) {
            case ('w'): {
                if (y - 1 > 0) { // далее проверка на цифру или букву новой координаты
                    place = (game_field.getField()[y - 1][x]);
                    if (((game_field.getField()[y - 1][x]) <= 57 && (game_field.getField()[y - 1][x]) >= 49 ) || ((game_field.getField()[y - 1][x]) < 123 && (game_field.getField()[y - 1][x]) > 96)) {
                        System.out.println("Вы не можете туда пойти. Клетка занята другим юнитом. ");
                    }
                    else {
                        check = true;
                        unit.setPrev_place(unit.getPlace());
                        unit.setPlace(place);
                    }
                }
                else {
                    System.out.println("Вы не можете туда пойти. Граница поля. ");
                }
                break;
            }
            case ('a'): {
                if (x - 1 > 0) {
                    place = (game_field.getField()[y][x-1]);
                    if (((game_field.getField()[y][x-1]) <= 57 && (game_field.getField()[y][x-1]) >= 49 ) || ((game_field.getField()[y][x-1]) < 123 && (game_field.getField()[y][x-1]) > 96)) {
                        System.out.println("Вы не можете туда пойти. Клетка занята другим юнитом. ");
                    }
                    else {
                        check = true;
                        unit.setPrev_place(unit.getPlace());
                        unit.setPlace(place);
                    }
                }
                else {
                    System.out.println("Вы не можете туда пойти. Граница поля. ");
                }
                break;
            }
            case ('s'): {
                if (y + 1 < 15) {
                    place = (game_field.getField()[y + 1][x]);
                    if (((game_field.getField()[y + 1][x]) <= 57 && (game_field.getField()[y + 1][x]) >= 49 ) || ((game_field.getField()[y + 1][x]) < 123 && (game_field.getField()[y + 1][x]) > 96)) {
                        System.out.println("Вы не можете туда пойти. Клетка занята другим юнитом. ");
                    }
                    else {
                        check = true;
                        unit.setPrev_place(unit.getPlace());
                        unit.setPlace(place);
                    }
                }
                else {
                    System.out.println("Вы не можете туда пойти. Граница поля. ");
                }
                break;
            }
            case ('d'): {
                if (x + 1 < 15) {
                    place = (game_field.getField()[y][x+1]);
                    if (((game_field.getField()[y][x + 1]) <= 57 && (game_field.getField()[y][x + 1] ) >= 49 ) || ((game_field.getField()[y][x + 1]) < 123 && (game_field.getField()[y][x + 1]) > 96)) {
                        System.out.println("Вы не можете туда пойти. Клетка занята другим юнитом. ");
                    }
                    else {
                        check = true;
                        unit.setPrev_place(unit.getPlace());
                        unit.setPlace(place);
                    }
                }
                else {
                    System.out.println("Вы не можете туда пойти. Граница поля. ");
                }
                break;
            }
        }
        return check;
    }

    public void attacking(Unit unit, ArrayList<Unit> enemy_remaining_characters, Set<Integer> remaining_enemy_count) {
        System.out.println("Выберите, какого противника вы хотите атаковать.");
        double distance;
        char enem = check_answer();
        int ind = ((enem - 48) - '0') - 1;
        Unit enemy = enemy_remaining_characters.get(ind);
        distance = Math.pow((Math.pow(unit.getPositionx() - enemy.getPositionx(), 2) + Math.pow(unit.getPositiony() - enemy.getPositiony(), 2)), 0.5);
        if (distance<= unit.getAttack_range()) {
            unit.attack(enemy);
            if (enemy.getHealth()<=0) {
                System.out.println("Враг " + enemy.getName() + " мертв.");
                remaining_enemy_count.remove(ind);
            }
        }
        else {
            System.out.println("Вы не можете атаковать врага " + enem + ", он слишком далеко.");
        }
    }
    public void moving(Unit unit, Field game_field) {
        unit.setAvailable_moving_range(unit.getMoving_range());
        System.out.println( "Вы можете пройти максимум " + unit.getAvailable_moving_range() + " клеток");
        while (unit.getAvailable_moving_range() >= 1) {
            System.out.println("\nУправляйте юнитом с помощью клавиш w, a, s, d. \n" +
                    "Нажмите q, чтобы заранее закончить движение юнита.");
            Scanner scanner = new Scanner(System.in);
            char mov = scanner.next().charAt(0);
            if (mov == 'q') {
                break;
            }
            if (check_if_free_and_safe(unit, mov, game_field)) {
                unit.move(mov);
                unit.setAvailable_moving_range(unit.fine_for_obstacles(unit.getPositionx(), unit.getPositiony(), game_field));
            } else {
                System.out.println("Попробуйте еще раз");
                scanner = new Scanner(System.in);
                mov = scanner.next().charAt(0);
                if (check_if_free_and_safe(unit, mov, game_field)) {
                    unit.move(mov);
                    unit.setAvailable_moving_range(unit.fine_for_obstacles(unit.getPositionx(), unit.getPositiony(), game_field));
                }
            }
            game_field.update_field(unit);
        }
    }
}
// количество мувов за ход
//текст поправить
// геймовер