package gutu;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	public static String regexPat
    = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
	private Pattern pattern = Pattern.compile(regexPat);
	private ArrayList<Token> queue = new ArrayList<Token>();
	private boolean hasMore;
	private LineNumberReader reader;

	public Lexer(Reader r) {
		hasMore = true;
		reader = new LineNumberReader(r);
	}

	public Token read() throws ParseException {
		if (fillQueue(0)) {
			return queue.remove(0);
		}
		return Token.EOF;
	}

	public Token peek(int i) throws ParseException {
		if (fillQueue(i)) {
			return queue.get(i);
		}
		return Token.EOF;
	}

	private boolean fillQueue(int i) throws ParseException {
		while (queue.size() <= i) {
			if (!hasMore) {
				return false;
			}
			readLine();
		}
		return true;
	}

	protected void readLine() throws ParseException {
		String line;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			throw new ParseException(e);
		}
		if (line == null) {
			hasMore = false;
			return;
		}
		int lineNo = reader.getLineNumber();
		Matcher matcher = pattern.matcher(line);
		matcher.useTransparentBounds(true).useAnchoringBounds(false);
		int pos = 0;
		int endPos = line.length();
		while (pos < endPos) {
			 matcher.region(pos, endPos);
			if (!matcher.lookingAt()) {
				throw new ParseException("bad token at line " + lineNo);
			}
			addToken(lineNo, matcher);
			pos = matcher.end();
		}
		queue.add(new IdToken(lineNo, Token.EOL));
	}

	protected void addToken(int lineNo, Matcher matcher) {
		String m = matcher.group(1);
		if (m == null) {
			// スペース
			return;
		}
		if (matcher.group(2) != null) {
			// コメント
			return;
		}
		if (matcher.group(3) != null) {
			// 数字
			queue.add(new NumToken(lineNo, Integer.parseInt(m)));
			return;
		}
		if (matcher.group(4) != null) {
			// リテラル
			queue.add(new StrToken(lineNo, toStringLiteral(m)));
			return;
		}
		// 識別子
		queue.add(new IdToken(lineNo, m));
	}

	private String toStringLiteral(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1, len = s.length(); i < len; i++) {
			char c = s.charAt(i);
			if (c == '\\' && i + 1 < len) {
				// エスケープシーケンスの処理
				switch (s.charAt(i + 1)) {
				case '"':
				case '\\':
					sb.append(s.charAt(++i));
					break;
				case 'n':
					++i;
					sb.append('\n');
				}
				continue;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	protected static class NumToken extends Token {
		private int value;

		protected NumToken(int line, int value) {
			super(line);
			this.value = value;
		}

		public boolean isNumber() {
			return true;
		}

		public String getText() {
			return Integer.toString(value);
		}

		public int getNumber() {
			return value;
		}
	}

	protected static class IdToken extends Token {
		private String id;

		protected IdToken(int line, String id) {
			super(line);
			this.id = id;
		}

		public boolean isIdentifier() {
			return true;
		}

		public String getText() {
			return id;
		}
	}

	protected static class StrToken extends Token {
		private String literal;

		protected StrToken(int line, String literal) {
			super(line);
			this.literal = literal;
		}

		public boolean isString() {
			return true;
		}

		public String getText() {
			return literal;
		}
	}
}
