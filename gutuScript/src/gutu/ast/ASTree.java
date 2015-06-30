package gutu.ast;

import java.util.Iterator;

public abstract class ASTree implements Iterable<ASTree>{

	public abstract ASTree chile(int i);
	public abstract int numChildren();
	public abstract Iterator<ASTree> children();
	public abstract String location();
	public Iterator<ASTree> iterator(){
		return children();
	}

}
