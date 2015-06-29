package gutu;

public abstract class Token {
	public static final Token EOF=new Token(-1){};
	public static final String EOL="\\n";
	private int lineNumber;

	public Token(int line) {
		// TODO 自動生成されたコンストラクター・スタブ
		lineNumber=line;
	}
	public int getLineNumber(){
		return lineNumber;
	}
	public boolean isIdentifier(){
		return false;
	}
	public boolean isNumber(){
		return false;
	}
	public boolean isString(){
		return false;
	}
	public String getText(){
		return "";
	}

}
