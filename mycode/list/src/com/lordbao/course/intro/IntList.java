package com.lordbao.course.intro;

public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if(rest==null) return 1;
		return 1+rest.size();
	}



	/** Returns the ith value in this list.*/
	public int get(int i) {
		if(i==0){
			return first;
		}
		return rest.get(i-1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("The IntList is {");
		IntList p = this;
		while (p!=null){
			sb.append(p.first).append(",");
			p=p.rest;
		}
		sb.append("}.");
		return sb.toString();
	}
}