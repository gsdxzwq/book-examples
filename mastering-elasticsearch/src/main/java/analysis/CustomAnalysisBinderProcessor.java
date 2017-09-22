package analysis;

import org.elasticsearch.index.analysis.AnalysisModule.AnalysisBinderProcessor;

/**
 * @ClassName: CustomAnalysisBinderProcessor
 * @Description: TODO
 * @author: zhaowq
 * @date: 2017年4月10日 下午3:18:19
 */
public class CustomAnalysisBinderProcessor extends AnalysisBinderProcessor {
	@Override
	public void processAnalyzers(AnalyzersBindings analyzersBindings) {
		analyzersBindings.processAnalyzer("mastering_analyzer", CustomAnalyzerProvider.class);
	}

	@Override
	public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
		tokenFiltersBindings.processTokenFilter("mastering_filter", CustomFilterFactory.class);
	}
}
