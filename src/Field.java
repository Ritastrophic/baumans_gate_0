import java.util.ArrayList;
import java.util.Random;

public class Field {
    private char[][] field;
    private int height;
    private int width;

    public Field(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public char[][] getField() {
        char[][] f = new char[height][width];
        for (int i = 0;i < 15; i++) {
            for(int j = 0;j < 15; j++) {
                f[i][j] = field[i][j];
            }
        }
        return f;
    }
    public void showField() {
        for (int i = 0;i < 15; i++) {
            System.out.println();
            for (int j = 0; j < 15; j++) {
                System.out.print(this.field[i][j]);
                System.out.print(' ');
            }
        }
        System.out.println();
    }
    public void setField(ArrayList<Unit> characters, ArrayList<Unit> enemy_characters) {
        System.out.println("\nВы с юнитами направляетесь на битву с захватчиками города." +
                "\nОбозначения на карте:\n" +
                "* - Oбычная клетка\n" +
                "# - Болото\n" +
                "@ - Холм\n" +
                "! – Лес\n" +
                "1, 2, 3 – Ваши юниты\n" +
                "a, b, c – Юниты противника\n"+
                "Каждая необычная клетка накладывает ограничение на передвижение каждого вида юнитов:\n");
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
        this.field = new char[15][15];
        for (int i = 0;i < 15; i++) {
            this.field[i] = new char[15];
            for(int j = 0;j < 15; j++) {
                this.field[i][j]='*';
            }
        }
        create_obstacles();
        for (int i=0;i<characters.size();i++) {
            (characters.get(i)).setPositionx(i);
            (characters.get(i)).setPrev_positionx(i);
            int x_pos=(characters.get(i)).getPositionx();
            int y_pos=(characters.get(i)).getPositiony();
            (characters.get(i)).setPositiony(0);
            (characters.get(i)).setPrev_positiony(0);
            this.field[0][i]=(char)(i+1+ '0');
            (enemy_characters.get(i)).setPositionx(14-i);
            (enemy_characters.get(i)).setPositiony(14);
            this.field[14][14-i]=(char)('a'+i);
        }
        for (int i = 0;i < 15; i++) {
            System.out.println();
            for (int j = 0; j < 15; j++) {
                System.out.print(this.field[i][j]);
                System.out.print(' ');
            }
        }
    }
    public void update_field(Unit unit) {
        int x = unit.getPositionx();
        int y = unit.getPositiony();
        int y_prev = unit.getPrev_positiony();
        int x_prev = unit.getPrev_positionx();
        char prev_place = unit.getPrev_place();
        char temp = this.field[y_prev][x_prev];
        this.field[y_prev][x_prev]=prev_place;
        this.field[y][x]= temp;

        for (int i = 0; i < 15; i++) {
            System.out.println();
            for (int j = 0; j < 15; j++) {
                System.out.print(this.field[i][j]);
                System.out.print(' ');
            }
        }
        System.out.println();
    }
    public void create_obstacles() {
        Random rnx = new Random();
        int randx = rnx.nextInt(13);
        Random rny = new Random();
        int randy = rny.nextInt(11) + 1;
        this.field[randy][randx]='@';
        this.field[randy][randx+1]='@';
        this.field[randy+1][randx+1]='@';
        this.field[randy+1][randx]='@';                         //hills

        randx = rnx.nextInt(12);
        randy = rny.nextInt(10) + 1;
        for (int i=0;i<3;i++) {
            if (this.field[randy + i][randx] == '*') {
                this.field[randy + i][randx] = '!';             //trees |
            }
            else {
                randx = rnx.nextInt(12);
                randy = rny.nextInt(10) + 1;
                for (int j=0;j<3;j++) {
                    this.field[randy + j][randx] = '!';
                }
            }
        }

        randx = rnx.nextInt(10);
        randy = rny.nextInt(12) + 1;
        for (int i=0;i<3;i++) {
            if (this.field[randy+i][randx] == '*') {
                this.field[randy][randx + i] = '!';
            }                                               //trees -
            else {
                randx = rnx.nextInt(10);
                randy = rny.nextInt(12) + 1;
                for (int j=0;j<3;j++) {
                    this.field[randy][randx + i] = '!';
                }
            }
        }

        randx = rnx.nextInt(11);
        randy = rny.nextInt(12) + 2;
        if (this.field[randy][randx] == '*')
            this.field[randx][randy]='#';
        this.field[randx+1][randy]='#';
        this.field[randx+2][randy+1]='#';
        this.field[randx+3][randy+1]='#'; //swamp

    }
}
