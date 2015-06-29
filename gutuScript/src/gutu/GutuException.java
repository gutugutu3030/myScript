package gutu;

import gutu.ast.ASTree;

public class GutuException extends RuntimeException {
	public GutuException(String m){
		super(m);
	}
	public GutuException(String m,ASTree t){
		System.out.println(m+" "+t.location());
	}

}
