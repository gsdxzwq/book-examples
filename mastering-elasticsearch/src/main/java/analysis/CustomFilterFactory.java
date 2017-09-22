package analysis;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettings;

/**
 * @ClassName: CustomFilterFactory
 * @Description: TODO
 * @author: zhaowq
 * @date: 2017年4月10日 下午2:54:51
 */
public class CustomFilterFactory extends AbstractTokenFilterFactory {

	@Inject
	public CustomFilterFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings) {
		super(index, indexSettings, name, settings);
	}

	@Override
	public TokenStream create(TokenStream tokenStream) {
		return new CustomFilter(tokenStream);
	}

}
