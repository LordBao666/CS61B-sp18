package com.lordbao.hash;


/**
 * @Author Lord_Bao
 * @Date 2024/11/3 23:10
 * @Version 1.0
 */
public class MyHashSetTest {
    public static void test1() {
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
        MyHashSet<String> set = new MyHashSet<>();
        for (String s : arr) {
            set.add(s);
        }

        System.out.println(set.contains("iqdY"));
        System.out.println(set.contains("joker"));
        System.out.println(set.getSize());

    }

    public static void main(String[] args) {
        test1();
    }
}
