package org.julia.scripting;

import org.julia.jni.NativeUtils;
import org.julia.jni.swig.Julia4J;

import javax.script.*;
import java.io.IOException;
import java.io.Reader;

/**
 * jsr223 compatible Julia bridge implementation
 *
 *
 * Created by rss on 23/08/2018
 */
public class JuliaEngine  extends AbstractScriptEngine {
    static {
        try {
            NativeUtils.loadLibraryFromJar(NativeUtils.libnameToPlatform("julia4j"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JuliaEngine() {
        if (Julia4J.jl_is_initialized() == 0)
            Julia4J.jl_init();
    }

    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException {
        final Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        if (bindings != null) {
            StringBuilder builder = new StringBuilder();
            bindings.entrySet().forEach(entry -> {
                String formatStr;
                // TODO: add direct write of values without text transformations
                if (entry.getValue() instanceof Number) {
                    formatStr = "%s = %s;\n";
                } else {
                    formatStr = "%s = \"%s\";\n";
                }
                builder.append(String.format(formatStr, entry.getKey(), entry.getValue()));
            });
            if (builder.length() > 0) {
                Julia4J.jl_eval_string(builder.toString());
            }
        }

        return Julia4J.jl_eval_string(script);
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        final StringBuilder buffer = new StringBuilder();
        try {
            char[] arr = new char[0x1000];
            int numCharsRead;
            while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
                buffer.append(arr, 0, numCharsRead);
            }
        }
        catch(IOException e) {
            throw new ScriptException(e);
        }
        return eval(buffer.toString(), context);
    }

    @Override
    public Bindings createBindings() {
        return new JuliaEngineBindings();
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return new JuliaScriptEngineFactory();
    }
}
