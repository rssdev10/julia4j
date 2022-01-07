import org.julia.jni.swig.Julia4J;
import org.julia.jni.swig.SWIGTYPE_p_jl_value_t;
import org.julia.scripting.JuliaScriptEngineFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.script.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test of jsr223 Julia binding
 * <p>
 * Created by rss on 25/08/2018
 */
@SuppressWarnings("WeakerAccess")
public class Julia4JScriptingTest {

    @BeforeAll
    public static void checkJuliaEngine() {
        final List<String> engineList = listScriptingEngines();

        assertTrue(engineList.contains(JuliaScriptEngineFactory.ENGINE_NAME_JULIA));
    }

    private static List<String> listScriptingEngines() {
        final List<String> result = new ArrayList<>();
        ScriptEngineManager mgr = new ScriptEngineManager();
        for (ScriptEngineFactory factory : mgr.getEngineFactories()) {
            result.add(factory.getEngineName());
            System.out.println("ScriptEngineFactory Info");
            System.out.printf("\tScript Engine: %s (%s)\n", factory.getEngineName(), factory.getEngineVersion());
            System.out.printf("\tLanguage: %s (%s)\n", factory.getLanguageName(), factory.getLanguageVersion());
            for (String name : factory.getNames()) {
                System.out.printf("\tEngine Alias: %s\n", name);
            }
        }
        return result;
    }

    @Test
    public void testBasic() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("julia");

        // evaluate Julia code
        engine.eval("dump(:(x^2 + y^2))");

        Object result = engine.eval("2 ^ 10");
        long checkedResult = Julia4J.jl_unbox_int64((SWIGTYPE_p_jl_value_t) result);
        assertEquals(1024, checkedResult);
    }

    @Test
    public void testReturnValue() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("julia");

        engine.put("x", "hello");
        // print global variable "x"
        engine.eval("println(x);");
        // the above line prints "hello"

        // Now, pass a different script context
        ScriptContext newContext = new SimpleScriptContext();
        Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);

        // add new variable "x" to the new engineScope
        engineScope.put("x", "world");

        // execute the same script - but this time pass a different script context
        engine.eval("println(x);", newContext);
        // the above line prints "world"

        // Add some numeric values
        engineScope.put("a", 2);
        engineScope.put("b", 10);
        engine.eval("for i in 1:5 println(\"Value: $(a * b * i)\"); end", newContext);

        Object result = engine.eval("a + b", newContext);
        assertEquals(12, Julia4J.jl_unbox_int64((SWIGTYPE_p_jl_value_t) result));
    }

    @Test
    public void testReturnStringValue() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("julia");

        engine.put("x", "hello");
        // print global variable "x"
        engine.eval("res = join(fill(x, 10), \";\");");

        SWIGTYPE_p_jl_value_t result = (SWIGTYPE_p_jl_value_t)engine.eval("res");
        Julia4J.jl_show(result);

        String str = Julia4J.jl_unbox_string((SWIGTYPE_p_jl_value_t) result);
        assertEquals(String.join(";", Collections.nCopies(10, "hello")), str);
    }
}
