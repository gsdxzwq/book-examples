package analysis;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.util.Version;

/**
 * @ClassName: CustomAnalyzer
 * @Description: TODO
 * @author: zhaowq
 * @date: 2017年4月10日 下午2:59:09
 */
public class CustomAnalyzer extends Analyzer {
	private final Version version;

	public CustomAnalyzer(final Version version) {
		this.version = version;
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
		final Tokenizer src = new WhitespaceTokenizer(version, reader);
		return new TokenStreamComponents(src, new CustomFilter(src));
	}

}
