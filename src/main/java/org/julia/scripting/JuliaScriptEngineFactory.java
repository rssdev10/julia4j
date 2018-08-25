package org.julia.scripting;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by rss on 25/08/2018
 */
public class JuliaScriptEngineFactory implements ScriptEngineFactory {
    public static final String ENGINE_NAME_JULIA = "julia4j";

    @Override
    public String getEngineName() {
        return ENGINE_NAME_JULIA;
    }

    @Override
    public String getEngineVersion() {
        return "0.0.1";
    }

    @Override
    public List<String> getExtensions() {
        return Collections.singletonList("jl");
    }

    @Override
    public List<String> getMimeTypes() {
        return null;
    }

    @Override
    public List<String> getNames() {
        return Arrays.asList("julia", "julia4j");
    }

    @Override
    public String getLanguageName() {
        return "julia";
    }

    @Override
    public String getLanguageVersion() {
        return "1.0";
    }

    @Override
    public Object getParameter(String key) {
        return null;
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        return null;
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return null;
    }

    @Override
    public String getProgram(String... statements) {
        return null;
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new JuliaEngine();
    }
}
