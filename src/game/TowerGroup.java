package game;

import java.util.ArrayList;
import java.util.List;

import structures.SinglyLinkedList;
import utils.GameException;

public class TowerGroup {

    class TowerIndex {

        int id;
        String name;

        public TowerIndex(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    private SinglyLinkedList<Tower> list = new SinglyLinkedList<Tower>();
    private List<TowerIndex> dictionary = new ArrayList<TowerIndex>();
    
    public TowerGroup(String[] names) {
        for (int i = 0; i < names.length; i++) {
            try {
                this.searchByName(names[i]);
                throw new GameException("Torres não podem ter nomes iguais.");
            } catch (GameException e) {
                int id = this.list.addLast(new Tower(names[i]));
                dictionary.add(new TowerIndex(id, names[i]));
            }
        }
    }

    public Tower searchByName(String name) {
        for (int i = 0; i < this.dictionary.size(); i++) {
            if (this.dictionary.get(i).name == name) {
                return this.list.search(this.dictionary.get(i).id);
            }
        }

        throw new GameException("Não encontrei a torre " + name);
    }

    public void show() {
        List<Tower> towers = list.toArray();

        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).show();
        }
    }

    public void move(String from, String to) {
        Tower fromTower = this.searchByName(from);
        Tower toTower = this.searchByName(to);

        toTower.add(fromTower.remove());
    }
}
