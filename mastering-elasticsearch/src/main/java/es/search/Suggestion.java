package es.search;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: Suggestion
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhaowq
 * @date 2016年4月20日上午9:34:49
 *
 */
public class Suggestion {
    public static List<String> getPermutationSentence(List<List<String>> termArrays, int start) {

        if (CollectionUtils.isEmpty(termArrays))
            return Collections.emptyList();

        int size = termArrays.size();
        if (start < 0 || start >= size) {
            return Collections.emptyList();
        }

        if (start == size - 1) {
            return termArrays.get(start);
        }

        List<String> strings = termArrays.get(start);

        List<String> permutationSentences = getPermutationSentence(termArrays, start + 1);

        if (CollectionUtils.isEmpty(strings)) {
            return permutationSentences;
        }

        if (CollectionUtils.isEmpty(permutationSentences)) {
            return strings;
        }

        List<String> result = new ArrayList<String>();
        for (String pre : strings) {
            for (String suffix : permutationSentences) {
                result.add(pre + suffix);
            }
        }

        return result;
    }
    
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> list2 = new ArrayList<String>();
        list2.add("1");
        list2.add("4");
        list2.add("3");
        list2.add("2");
        list.add(list2);
        List<String> list3 = getPermutationSentence(list, 3);
        System.out.println(list3);
    }
}
