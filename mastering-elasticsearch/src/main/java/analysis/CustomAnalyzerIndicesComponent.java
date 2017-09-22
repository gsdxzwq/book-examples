package analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.analysis.AnalyzerScope;
import org.elasticsearch.index.analysis.PreBuiltAnalyzerProviderFactory;
import org.elasticsearch.index.analysis.PreBuiltTokenFilterFactoryFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.IndicesAnalysisService;

/**
 * @ClassName: CustomAnalyzerIndicesComponent
 * @Description: TODO
 * @author: zhaowq
 * @date: 2017年4月10日 下午3:27:10
 */
public class CustomAnalyzerIndicesComponent extends AbstractComponent {

	@Inject
	public CustomAnalyzerIndicesComponent(Settings settings, IndicesAnalysisService indicesAnalysisService) {
		super(settings);
		indicesAnalysisService.analyzerProviderFactories().put("mastering_analyzer",
				new PreBuiltAnalyzerProviderFactory("mastering_analyzer", AnalyzerScope.INDICES, new CustomAnalyzer(Version.LUCENE_CURRENT)));
		indicesAnalysisService.tokenFilterFactories().put("mastering_filter", new PreBuiltTokenFilterFactoryFactory(new TokenFilterFactory() {

			@Override
			public String name() {
				return "mastering_filter";
			}

			@Override
			public TokenStream create(TokenStream tokenStream) {
				return new CustomFilter(tokenStream);
			}
		}));
	}
}
