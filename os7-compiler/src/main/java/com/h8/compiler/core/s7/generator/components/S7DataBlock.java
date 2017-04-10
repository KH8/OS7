package com.h8.compiler.core.s7.generator.components;

import com.h8.compiler.core.s7.snippets.SnippetParameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S7DataBlock implements S7CodeComponent {
    private String name;
    private String title;
    private String version;
    private Boolean retain;
    private S7CodeComponents<S7Parameter> parameters;
    private S7CodeComponents<S7ParameterValue> parameterValues;

    @Override
    public SnippetParameter toSnippetParameter() {
        return SnippetParameter.nestedParameters()
                .with("NAME", name)
                .with("TITLE", title)
                .with("VERSION", version)
                .with("RETAIN", retain.toString())
                .with("PARAMETERS", parameters.toSnippetParameter())
                .with("PARAMETERS_VALUES", parameterValues.toSnippetParameter());
    }
}
