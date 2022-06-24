package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

import java.util.LinkedList;

public class HashTable {
    private class Entry {
        int k;
        String v;

        Entry(int k, String v) {
            this.k = k;
            this.v = v;
        }
    }

    private LinkedList<Entry>[] entries = new LinkedList[10];

    public void put(int k, String v) {
        Entry entry = getEntryByKey(k);
        if (entry != null) {
            entry.v = v;
            return;
        }

        getOrCreateSlot(k).add(new Entry(k, v));
    }

    public String get(int k) {
        Entry entry = getEntryByKey(k);
        return (entry == null) ? null : entry.v;
    }

    public void remove(int k) {
        Entry entry = getEntryByKey(k);
        if (entry == null)
            throw new IllegalStateException();
        getSlot(k).remove(entry);
    }

    private Entry getEntryByKey(int k) {
        LinkedList<Entry> slot = getSlot(k);
        if (slot != null)
            for (Entry e : slot)
                if (e.k == k)
                    return e;
        return null;
    }

    private LinkedList<Entry> getSlot(int k) {
        int i = hash(k);
        return entries[i];
    }

    private LinkedList<Entry> getOrCreateSlot(int k) {
        int i = hash(k);
        if (entries[i] == null)
            entries[i] = new LinkedList<Entry>();

        return entries[i];
    }

    private int hash(int k) {
        return Math.abs(k) % entries.length;
    }

}