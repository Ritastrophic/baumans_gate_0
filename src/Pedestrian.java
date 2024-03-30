public class Pedestrian extends Unit {
    public Pedestrian(String name, int health, int attack_force, int attack_range, int shield, int moving_range, double available_moving_range, int cost, char prev_place,  char place) {
        super(name, health, attack_force, attack_range, shield, moving_range, available_moving_range, cost, prev_place, place);
    }
    @Override public double fine_for_obstacles(int positionx, int positiony, Field game_field) { //штрафы за препятствия
        switch ((game_field.getField())[positionx][positiony]) {
            case ('!'): {// tree
                this.setAvailable_moving_range(this.getAvailable_moving_range() - 1.2);
                break;
            }
            case ('#'): {// swamp
                this.setAvailable_moving_range(this.getAvailable_moving_range() - 1.5);
                break;
            }
            case ('@'): {// hill
                this.setAvailable_moving_range(this.getAvailable_moving_range() - 2);
                break;
            }
            case ('*'): { //usual
                this.setAvailable_moving_range(this.getAvailable_moving_range() - 1);
                break;
            }
        }
        return this.getAvailable_moving_range();
    }
}
