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

    private TowerIndex searchIndexByName(String name) {
        for (int i = 0; i < this.dictionary.size(); i++) {
            if (this.dictionary.get(i).name.equals(name)) {
                return this.dictionary.get(i);
            }
        }

        return null;
    }

    public Tower searchByName(String name) {
        TowerIndex index = this.searchIndexByName(name);

        if (index == null) {
            throw new GameException("Não encontrei a torre " + name);
        }

        Tower result = this.list.search(index.id);

        if (result == null) {
            throw new GameException("Não encontrei a torre " + name);
        }

        return result;
    }

    public void show() {
        List<Tower> towers = list.toArray();

        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).show2();
        }
    }

    public void replace(String name, Tower tower) {
        TowerIndex byeIndex = this.searchIndexByName(name);
        int newId = this.list.addAfter(tower, byeIndex.id);

        this.list.remove(byeIndex.id);

        dictionary.remove(byeIndex);
        dictionary.add(new TowerIndex(newId, name));
    }

    public void move(String from, String to) throws GameException {
        Tower fromTower = this.searchByName(from);
        Tower toTower = this.searchByName(to);

        if (fromTower.isEmpty()) {
            throw new GameException("A torre " + fromTower.name + " está vazia.");
        }

        Integer moving = fromTower.peek();

        toTower.add(moving);
        fromTower.remove();
    }
}
