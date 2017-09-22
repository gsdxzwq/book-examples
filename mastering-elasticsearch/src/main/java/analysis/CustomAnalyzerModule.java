package analysis;

import org.elasticsearch.common.inject.AbstractModule;

/**
 * @ClassName: CustomAnalyzerModule
 * @Description: TODO
 * @author: zhaowq
 * @date: 2017年4月10日 下午3:40:14
 */
public class CustomAnalyzerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CustomAnalyzerIndicesComponent.class).asEagerSingleton();
	}
}
