package com.lordbao.hash;


import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/3 21:51
 * @Version 1.0
 */
public class MyHashMapTest {

    public static void test1() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.add(1, "apple");
        map.add(2, "banana");
        map.add(3, "carrot");
        map.add(4, "duck");
        map.add(5, "egg");
        map.add(6, "ferry");
        map.add(7, "grapes");
        map.add(8, "horn");
        map.add(9, "iron");
        map.add(10, "joker");
        map.add(10, "jojo");
        map.add(11, "king");
        map.add(12, "lord");
        map.add(13, "marry");
        map.add(14, "nope");
        map.add(15, "oooops");
        map.add(16, "pink");
        map.add(17, "queen");

        System.out.println(map.get(3));
        System.out.println(map.contains(3));
        System.out.println(map.contains(-1));
        System.out.println(map.getKeys());
        System.out.println(map.getVals());
        System.out.println(map.getEntrySet());
        System.out.println(map.getSize());
        map.printHashTables();


        System.out.println("----------------");
        MyHashMap<String, Integer> map2 = new MyHashMap<>(.75);
        List<MyHashMap<Integer, String>.EntrySet> entrySet = map.getEntrySet();
        for (MyHashMap<Integer, String>.EntrySet item : entrySet) {
            map2.add(item.val, item.key);
        }

        System.out.println(map2.contains("banana"));
        System.out.println(map2.contains("orange"));
        map2.printHashTables();
    }

    public static void test2() {
        String[] arr = {
                "qCJfeG0DM", "G1xgHf7wK", "5BxZOgJ4", "jVLDcjts", "xA", "6hTNG", "nemuhDp", "7FRQyEi",
                "5USds", "6DlcsFKQZ", "lC", "GpaAN", "XJvy", "v", "yw8K06S", "i2lIUHg", "ZMj", "I3ti4UG5b",
                "NXd", "a4eNca", "wtGBvcIU", "M", "R3x", "issgkaKm", "N", "m81", "IAxi", "FrFaCiO", "AeeU",
                "05HR", "f79eL6ta", "GLjk", "auuK", "M03BD6leho", "QC", "frjEK", "4kEz53V", "4NJBLk77M",
                "tR7X3tmV", "iwxF", "WBhX0UrL", "yuda", "DGsSM", "XZit", "VPsOO", "cejCn", "ON6", "ZsG7",
                "VH3ohk", "p7", "Gcm", "Enk3rUbev", "K62LTPf", "gkwzxW8w", "ffHDCA", "RD", "iwTp", "f",
                "7fEZk7", "GO4HyHJmuG", "4j8i8", "Ssi9F", "XgDDp1ZfD", "bLl69Od", "iKxRwbw", "SN",
                "mhznWH2D5", "cHzXt", "uP", "vAixufod", "15bEaOq2", "Jh211Usb", "S", "uyw", "iWNkX4T01g",
                "2gTK", "8Ny3iizNy", "bOd96W3rG", "FMABK3Q", "TNV4", "iIQ", "ZZC9do1FE", "pr", "HYnnedR",
                "FoKoST", "8ZcmO8c", "m0B", "m", "ox9Ki", "D0lX", "E", "H", "vz2f7792qs", "ASerxChZXc",
                "Zo", "tvR", "UhLcgRXs7", "vivo", "wiSWQkZ", "iqdY"
        };

//        MyHashMap<String,Integer> map = new MyHashMap<>();
        MyHashMap<String, Integer> map = new MyHashMap<>(.75);
        for (int i = 0; i < arr.length; i++) {
            map.add(arr[i], i);
        }

    }

    public static void main(String[] args) {
        test1();
//        test2();


    }
}
