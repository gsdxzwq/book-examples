/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: Suggester.java 
 * @Prject: es-test
 * @Package: com.zhaowq.es.search 
 * @Description: TODO
 * @author: zhaowq   
 * @date: 2016年9月28日 下午8:06:39 
 * @version: V1.0   
 */
package es.search;

import java.util.ArrayList;
import java.util.List;

import es.commons.ElasticSearchUtils;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;

/**
 * @ClassName: Suggester
 * @Description: TODO
 * @author: zhaowq
 * @date: 2016年9月28日 下午8:06:39
 */
public class Suggester {
	public static List<String> getCompletionSuggest(String prefix) {
		CompletionSuggestionBuilder csbuilder = new CompletionSuggestionBuilder("autocomplete");
		csbuilder.text(prefix);
		csbuilder.field("title");
		csbuilder.size(10);
		Client client = ElasticSearchUtils.getClientInstance();
		SuggestResponse response = client.prepareSuggest("").addSuggestion(csbuilder).execute().actionGet();
		List<? extends Entry<? extends Option>> list = response.getSuggest().getSuggestion("autocomplete").getEntries();
		List<String> suggests = new ArrayList<String>();
		if (list == null) {
			return null;
		} else {
			for (Entry<? extends Option> e : list) {
				for (Option option : e) {
					suggests.add(option.getText().toString());
				}
			}
			return suggests;
		}
	}
}
