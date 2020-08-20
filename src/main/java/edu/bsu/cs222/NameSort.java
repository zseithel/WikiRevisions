package edu.bsu.cs222;

import java.util.ArrayList;

public class NameSort {
    public ArrayList<String> names;
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public NameSort(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<String> alphabetizeNames() {
        int indexForNames = 0;
        int indexForAlphabet = 0;
        int swapIndex = 0;
        while (swapIndex < names.size() - 1) {
            Character letterOfAlphabet = alphabet.charAt(indexForAlphabet);
            Character firstLetterOfName = names.get(indexForNames).charAt(0);
            if (firstLetterOfName.equals(letterOfAlphabet)) {
                swap(swapIndex, indexForNames);
                swapIndex++;
            } else {
                if (indexForNames == names.size() - 1) {
                    indexForNames = 0;
                    indexForAlphabet++;
                } else {
                    indexForNames++;
                }
                if (indexForAlphabet == 26) {
                    break;
                }
            }
        }
        return names;
    }

    public void swap(int swapIndex, int nameIndex) {
        String holder = names.get(swapIndex);
        names.set(swapIndex, names.get(nameIndex));
        names.set(nameIndex, holder);
    }
}