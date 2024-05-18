package com.lordbao.exercise.sllist;

/**
 * @Author Lord_Bao
 * @Date 2024/5/18 16:37
 * @Version 1.0
 *
 * problem 1:
 * the link is https://www.kartikkapur.com/documents/mt1.pdf#page=7
 * We want to add a method to IntList so that if 2 numbers in a row are the same, we add them together and
 * make one large node. For example:
 * 1 → 1 → 2 → 3 becomes 2 → 2 → 3 which becomes 4 → 3
 *
 * problem 2:
 * Modify the Intlist class so that every time you add a value you “square” the IntList.
 * For example, upon the insertion of 5, the below IntList would transform from:
 * 1 => 2 to 1 => 1 => 2 => 4 => 5
 *
 * and if 7 was added to the latter IntList, it would become
 * 1 => 1 => 1 => 1 => 2 => 4 => 4 => 16 => 5 => 25 => 7
 *
 * Additionally, you are provided the constraint that you can only access the size() function one time
 * during the entire process of adding a node.
 */
public class IntList {
    public int first ;
    public IntList rest;
    public IntList (int f,IntList r){
        first=f;
        rest=r;
    }

    public  void  addAdjacent(){
        /*Your code here*/
        IntList p =this;
        IntList q= rest;
        while (q!=null){
            if(p.first== q.first){
                //we store the sum in p and delete q
                p.first=p.first+q.first;
                p.rest=q.rest;
                q=q.rest;
            }else {
                p=p.rest;
                q=q.rest;
            }
        }
    }

    public  void  squareAdd(int x){
        /*Your code here*/
        IntList p =this;

        while (p.rest!=null){
            IntList q =p.rest;
            p.rest=new IntList(p.first * p.first,q);
            p=q;
        }

        p.rest = new IntList(p.first*p.first,new IntList(x,null));
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("IntList { ");
        int size=0;
        IntList p =this;
        while (p!=null){
            sb.append(p.first).append(" ");
            p=p.rest;
            size++;
        }
        sb.append(" }.").append("size : " ).append(size);
        return sb.toString();
    }

    public static void main(String[] args) {
        //test addAdjacent()
        IntList a = new IntList(3,null);
        a=new IntList(2,a);
        a=new IntList(1,a);
        a=new IntList(1,a);
        System.out.println(a);
        a.addAdjacent();
        System.out.println(a);

        //test squareAdd
        IntList b = new IntList(2,null);
        b=new IntList(1,b);
        b.squareAdd(5);
        System.out.println(b);
        b.squareAdd(7);
        System.out.println(b);
    }
}
