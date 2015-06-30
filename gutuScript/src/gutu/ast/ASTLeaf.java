package gutu.ast;

import java.util.ArrayList;
import java.util.Iterator;

import gutu.Token;

public class ASTLeaf extends ASTree {
	private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
	protected Token token;

	public ASTLeaf(Token token) {
		this.token = token;
	}

	@Override
	public ASTree chile(int i) {
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int numChildren() {
		return 0;
	}

	@Override
	public Iterator<ASTree> children() {
		// TODO 自動生成されたメソッド・スタブ
		return empty.iterator();
	}

	@Override
	public String location() {
		// TODO 自動生成されたメソッド・スタブ
		return "at line "+token.getLineNumber();
	}

	public String toString(){
		return token.getText();
	}
	public Token token(){
		return token;
	}

}
