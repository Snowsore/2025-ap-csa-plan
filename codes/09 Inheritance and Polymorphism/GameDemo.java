import java.util.*;

class Character {
    String name;
    int hp;
    int baseAtk;

    Character(String name, int hp, int baseAtk) {
        this.name = name;
        this.hp = hp;
        this.baseAtk = baseAtk;
    }

    void attack(Character target, Random rng) {
        int dmg = baseAtk;
        target.hp -= dmg;
        System.out.println(name + " hits " + target.name + " for " + dmg);
    }

    boolean alive() {
        return hp > 0;
    }

    void printStatus() {
        System.out.println(name + " HP=" + Math.max(hp, 0));
    }
}

class Warrior extends Character {
    Warrior(String name) {
        super(name, 60, 10);
    }

    @Override
    void attack(Character target, Random rng) {
        int crit = rng.nextInt(100) < 25 ? baseAtk : 0;
        int dmg = baseAtk + crit;
        target.hp -= dmg;
        System.out.println(name + " slashes " + target.name + " for " + dmg + (crit > 0 ? " (CRIT!)" : ""));
    }

    void guard() {
        hp += 3;
        System.out.println(name + " guards and recovers 3");
    }
}

class Mage extends Character {
    Mage(String name) {
        super(name, 40, 7);
    }

    @Override
    void attack(Character target, Random rng) {
        int splash = 3;
        int dmg = baseAtk + rng.nextInt(4);
        target.hp -= dmg;
        System.out.println(name + " casts Bolt on " + target.name + " for " + dmg + " and burns for " + splash);
        target.hp -= splash;
    }

    void heal(Character ally) {
        ally.hp += 6;
        System.out.println(name + " heals " + ally.name + " for 6");
    }
}

class Rogue extends Character {
    Rogue(String name) {
        super(name, 45, 8);
    }

    @Override
    void attack(Character target, Random rng) {
        int hits = 1 + (rng.nextInt(100) < 35 ? 1 : 0);
        int total = 0;
        for (int i = 0; i < hits; i++)
            total += baseAtk;
        target.hp -= total;
        System.out.println(name + " strikes " + target.name + " x" + hits + " for " + total);
    }

    void pokeWeakSpot(Character target) {
        target.hp -= 5;
        System.out.println(name + " pokes weak spot of " + target.name + " for 5");
    }
}

class Boss extends Character {
    Boss(String name) {
        super(name, 300, 9);
    }

    @Override
    void attack(Character target, Random rng) {
        int dmg = baseAtk + rng.nextInt(6);
        target.hp -= dmg;
        System.out.println(name + " smashes " + target.name + " for " + dmg);
    }

    void roar(List<Character> party) {
        System.out.println(name + " ROARS! Everyone takes 4");
        for (Character c : party)
            c.hp -= 4;
    }
}

public class GameDemo {
    public static void main(String[] args) {
        Random rng = new Random();

        Character warrior = new Warrior("Ares");
        Character mage = new Mage("Lyra");
        Character rogue = new Rogue("Shade");
        List<Character> party = Arrays.asList(warrior, mage, rogue);

        Character boss = new Boss("Stone Golem");

        int round = 1;
        while (anyAlive(party) && boss.alive() && round <= 10) {
            System.out.println("\n-- Round " + round + " --");

            for (Character c : party) {
                if (!c.alive() || !boss.alive())
                    continue;

                c.attack(boss, rng);

                if (c instanceof Warrior && rng.nextInt(100) < 30) {
                    Warrior w = (Warrior) c;
                    w.guard();
                } else if (c instanceof Mage && rng.nextInt(100) < 30) {
                    Character low = lowestHpAlive(party);
                    if (low != null) {
                        Mage m = (Mage) c;
                        m.heal(low);
                    }
                } else if (c instanceof Rogue && rng.nextInt(100) < 30) {
                    Rogue r = (Rogue) c;
                    r.pokeWeakSpot(boss);
                }
            }

            if (boss.alive()) {
                List<Character> alive = aliveList(party);
                if (!alive.isEmpty()) {
                    if (rng.nextInt(100) < 35) {
                        Boss b = (Boss) boss;
                        b.roar(alive);
                    } else {
                        Character target = alive.get(rng.nextInt(alive.size()));
                        boss.attack(target, rng);
                    }
                }
            }

            showStatus(party, boss);
            round++;
        }

        System.out.println("\n=== Result ===");
        if (!boss.alive())
            System.out.println("Party wins!");
        else if (!anyAlive(party))
            System.out.println("Boss wins!");
        else
            System.out.println("Time up.");
    }

    static boolean anyAlive(List<Character> party) {
        for (Character c : party)
            if (c.alive())
                return true;
        return false;
    }

    static List<Character> aliveList(List<Character> party) {
        List<Character> res = new ArrayList<>();
        for (Character c : party)
            if (c.alive())
                res.add(c);
        return res;
    }

    static Character lowestHpAlive(List<Character> party) {
        Character pick = null;
        for (Character c : party)
            if (c.alive() && (pick == null || c.hp < pick.hp))
                pick = c;
        return pick;
    }

    static void showStatus(List<Character> party, Character boss) {
        for (Character c : party)
            c.printStatus();
        boss.printStatus();
    }
}
