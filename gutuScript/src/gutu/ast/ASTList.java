package gutu.ast;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree{
	protected List<ASTree> children;
	public ASTList(List<ASTree> chisldren){
		this.children=children;
	}

	@Override
	public ASTree chile(int i) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int numChildren() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public Iterator<ASTree> children() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String location() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
