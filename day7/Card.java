package day7;

import java.util.*;

public class Card {
    public static Map<String, Integer> lookup = new HashMap<String, Integer>();
    private String cards;
    public Integer bet;
    public Integer type;

    static {
        lookup.put("A", 13);
        lookup.put("K", 12);
        lookup.put("Q", 11);
        lookup.put("J", 10);
        lookup.put("T", 9);
        lookup.put("9", 8);
        lookup.put("8", 7);
        lookup.put("7", 6);
        lookup.put("6", 5);
        lookup.put("5", 4);
        lookup.put("4", 3);
        lookup.put("3", 2);
        lookup.put("2", 1);
    }

    public Card(String cards, Integer bet) {
        this.cards = cards;
        this.bet = bet;

        findType();
    }

    private void findType() {
        List<Character> types = new ArrayList<Character>();
        for (int i = 0; i < cards.length(); i++) {
            if (i == 0) {
                types.add(cards.charAt(i));
            } else {
                boolean isPresent = false;
                for (Character character : types) {
                    if (character == cards.charAt(i)) {
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    types.add(cards.charAt(i));
                }
            }
        }
        switch (types.size()) {
            case 1:
                this.type = 7;
                break;
            case 2:
                int amountOfFirst = 1;
                Character c = 'x';
                for (int i = 0; i < cards.length(); i++) {
                    if (i == 0) {
                        c = cards.charAt(i);
                    } else if (c.equals(cards.charAt(i))) {
                        amountOfFirst++;
                    }
                }

                switch (amountOfFirst) {
                    case 1:
                        this.type = 6;
                        break;
                    case 2:
                        this.type = 5;
                        break;
                }
                break;
            case 3:
                int[] amounts = new int[3];
                for (int i = 0; i < types.size(); i++) {
                    for (int j = 0; j < cards.length(); j++) {
                        if (cards.charAt(j) == types.get(i)) {
                            amounts[i] = amounts[i] + 1;
                        }
                    }
                }

                int pairs = 0;
                for (int i : amounts) {
                    if (i == 2) {
                        pairs++;
                    }
                }
                switch (pairs) {
                    case 0:
                        this.type = 4;
                        break;
                    case 1:
                        this.type = 2;
                        break;
                    case 2:
                        this.type = 3;
                        break;
                }
                break;
            default:
                this.type = 1;
                break;
        }
    }
}
