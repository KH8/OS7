package com.h8.compiler.core.s7.snippets;

import org.apache.commons.lang3.StringUtils;

public class SnippetFactory {
    public String create(S7Snippet snippet, SnippetParameter params) {
        String s = snippet.getSnippet();
        return replaceParameters(s, params);
    }

    private String replaceParameters(String s, SnippetParameter params) {
        for (String key : params.keySet()) {
            SnippetParameter sp = params.get(key);
            s = sp.isNested() ?
                    replaceNestedParameters(s, key, sp) :
                    replaceSingleParameter(s, key, sp);
        }
        return s;
    }

    private String replaceNestedParameters(String s, String key, SnippetParameter params) {
        String prefix = "@" + key + "[";
        Integer prefixIdx = s.indexOf(prefix);

        String whiteSpaces = getWhiteSpacesBeforeIndex(s, prefixIdx);

        String suffix = "]";
        Integer suffixIdx = s.indexOf(suffix, prefixIdx);

        String nested = s.substring(prefixIdx + prefix.length(), suffixIdx);
        String snippets = collectNestedSnippets(nested, params, whiteSpaces);

        return s.replace(whiteSpaces + prefix + nested + suffix, snippets);
    }

    private String getWhiteSpacesBeforeIndex(String s, Integer idx) {
        char[] chars = s.toCharArray();
        Integer i = idx - 1;
        while (i > 0 && chars[i] == ' ') {
            i--;
        }
        return StringUtils.repeat(" ", idx - i - 1);
    }

    private String collectNestedSnippets(String nested, SnippetParameter params, String whiteSpaces) {
        String s = "";
        for (String key : params.keySet()) {
            if (!s.isEmpty()) {
                s += System.getProperty("line.separator");
            }
            SnippetParameter sp = params.get(key);
            s += whiteSpaces;
            s += replaceParameters(nested, sp);
        }
        return s;
    }

    private String replaceSingleParameter(String s, String key, SnippetParameter param) {
        return s.replace("$" + key, param.getValue());
    }
}
