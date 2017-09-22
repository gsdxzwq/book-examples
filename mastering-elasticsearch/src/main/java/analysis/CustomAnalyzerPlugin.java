package analysis;

import java.util.Collection;

import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

/**
 * @ClassName: CustomAnalyzerPlugin
 * @Description: TODO
 * @author: zhaowq
 * @date: 2017年4月10日 下午3:44:35
 */
public class CustomAnalyzerPlugin extends AbstractPlugin {

	@Override
	public Collection<Class<? extends Module>> modules() {
		return ImmutableList.<Class<? extends Module>> of(CustomAnalyzerModule.class);
	}

	public void onModule(AnalysisModule module) {
		module.addProcessor(new CustomAnalysisBinderProcessor());
	}

	@Override
	public String name() {
		return "AnalyzerPlugin";
	}

	@Override
	public String description() {
		return "Custom analyzer plugin";
	}

}
