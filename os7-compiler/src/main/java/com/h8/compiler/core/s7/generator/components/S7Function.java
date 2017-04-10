package com.h8.compiler.core.s7.generator.components;

import com.h8.compiler.core.s7.snippets.SnippetParameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S7Function implements S7CodeComponent {
    private String name;
    private S7Type output;
    private String title;
    private String version;
    private S7CodeComponents<S7Parameter> inParameters;
    private S7CodeComponents<S7Parameter> inOutParameters;
    private S7CodeComponents<S7Parameter> tempParameters;
    private S7CodeComponents<S7Network> networks;

    @Override
    public SnippetParameter toSnippetParameter() {
        return SnippetParameter.nestedParameters()
                .with("NAME", name)
                .with("OUTPUT", output.getName())
                .with("TITLE", title)
                .with("VERSION", version)
                .with("IN_PARAMETERS", inParameters.toSnippetParameter())
                .with("IN_OUT_PARAMETERS", inOutParameters.toSnippetParameter())
                .with("TEMP_PARAMETERS", tempParameters.toSnippetParameter())
                .with("NETWORKS", networks.toSnippetParameter());
    }
}
