import java.lang.runtime.SwitchBootstraps;

public class Unit {
    private String name;
    private int health;
    private int attack_force;
    private int attack_range;
    private int shield;
    private int moving_range;
    private double available_moving_range;
    private int cost;
    private int positionx;
    private int positiony;
    private int prev_positionx;
    private int prev_positiony;
    private char prev_place;
    private char place;

    public Unit(String name, int health, int attack_force, int attack_range, int shield, int moving_range, double available_moving_range, int cost, char prev_place,  char place) {
        this.name = name;
        this.health = health;
        this.attack_force = attack_force;
        this.attack_range = attack_range;
        this.shield = shield;
        this.moving_range = moving_range;
        this.available_moving_range = available_moving_range;
        this.cost = cost;
        this.prev_place = prev_place;
        this.place = place;
    }

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int new_health) {
        this.health = new_health;
    }
    public int getAttack_force() {
        return attack_force;
    }
    public int getAttack_range() {
        return attack_range;
    }
    public int getMoving_range() {
        return moving_range;
    }
    public int getShield() {
        return shield;
    }
    public void setShield(int new_shield) {
        this.shield = new_shield;
    }
    public int getCost() {
        return cost;
    }

    public void setAvailable_moving_range(double new_range) {
        this.available_moving_range = new_range;
    }
    public double getAvailable_moving_range() {
        return available_moving_range;
    }

    public int getPositiony() {
        return positiony;
    }
    public void setPositiony(int positiony) {
        this.positiony = positiony;
    }
    public int getPositionx() {
        return positionx;
    }
    public void setPositionx(int positionx) {
        this.positionx = positionx;
    }
    public int getPrev_positionx() {
        return prev_positionx;
    }
    public void setPrev_positionx(int prev_positionx) {
        this.prev_positionx = prev_positionx;
    }
    public int getPrev_positiony() {
        return prev_positiony;
    }
    public void setPrev_positiony(int prev_positiony) {
        this.prev_positiony = prev_positiony;
    }

    public char getPrev_place() {
        return prev_place;
    }
    public void setPrev_place(char prev_place) {
        this.prev_place = prev_place;
    }
    public char getPlace() {
        return place;
    }
    public void setPlace(char place) {
        this.place = place;
    }
    public double fine_for_obstacles(int positionx, int positiony, Field game_field) {
        return this.getAvailable_moving_range();
    } //штрафы за препятствия

    public void attack(Unit enemy) {
        int force = this.getAttack_force();
        System.out.println("Attaaack!");
        System.out.println("Shield was " + enemy.getShield() + " attack was " + this.getAttack_force());
        System.out.println("Health was " + enemy.getHealth());
        if (enemy.getShield()-this.getAttack_force() >= 0) {
            enemy.setShield(enemy.getShield() - this.getAttack_force());
        }
        else {
            force-=enemy.getShield();
            System.out.println("осталось от атаки " + force);
            enemy.setShield(0);
            enemy.setHealth(enemy.getHealth()-force);
        }
        System.out.println("Shield is " + enemy.getShield() + " attack was " + this.getAttack_force());
        System.out.println("Health is " + enemy.getHealth());
    }
    public void move(char mov) {/*
        System.out.println("now " + this.positiony + " " + this.positionx + " " + this.place);
        System.out.println("prev " + this.prev_positiony + " " + this.prev_positionx+ " " + this.prev_place);*/
        switch(mov) {
            case ('w'): {
                this.setPrev_positiony(this.positiony);
                this.setPrev_positionx(this.positionx);
                this.setPositiony(this.positiony - 1);
                /*System.out.println("new " + this.positiony + " " + this.positionx);
                System.out.println("new_OLD " + this.prev_positiony + " " + this.prev_positionx);*/
                break;
            }
            case ('a'): {
                this.setPrev_positiony(this.positiony);
                this.setPrev_positionx(this.positionx);
                this.setPositionx(this.positionx - 1);
                /*System.out.println("new " + this.positiony + " " + this.positionx);
                System.out.println("new_OLD " + this.prev_positiony + " " + this.prev_positionx);*/
                break;
            }
            case ('s'): {
                this.setPrev_positiony(this.positiony);
                this.setPrev_positionx(this.positionx);
                this.setPositiony(this.positiony + 1);
               /* System.out.println("new " + this.positiony + " " + this.positionx);
                System.out.println("new_OLD " + this.prev_positiony + " " + this.prev_positionx);*/
                break;
            }
            case ('d'): {
                this.setPrev_positiony(this.positiony);
                this.setPrev_positionx(this.positionx);
                this.setPositionx(positionx + 1);
                /*System.out.println("new " + this.positiony + " " + this.positionx);
                System.out.println("new_OLD " + this.prev_positiony + " " + this.prev_positionx);*/
                break;
            }
            default: {
                System.out.println("Введен неверный символ");
                break;
            }
        }
    }
}
