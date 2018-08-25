# Julia for Java binding

This is initial Java binding implementation.
For now there are supported only basic functions
for running scripts and types converting.

See https://docs.julialang.org/en/stable/manual/embedding/

Class org.julia.jni.swig.Julia4J implements JNI calls to libjulia. See [example](src/test/java/Julia4JJNITest.java)

Classes from package class org.julia.scripting implement jsr233 scripting interface.  See [example](src/test/java/Julia4JScriptingTest.java)

### Examples

Example 1:
```java
import org.julia.jni.swig.Julia4J;

Julia4J.jl_init();
Julia4J.jl_eval_string("dump(:(1 + 2x^2))");
Julia4J.jl_atexit_hook(0);
```

Example 2:
```java
import org.julia.jni.swig.Julia4J;
import org.julia.jni.swig.SWIGTYPE_p_jl_value_t;
import org.julia.scripting.JuliaScriptEngineFactory;

ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEngineByName("julia");

// evaluate Julia code
engine.eval("dump(:(x^2 + y^2))");

Object result = engine.eval("2 ^ 10");
long checkedResult = Julia4J.jl_unbox_int64((SWIGTYPE_p_jl_value_t) result);
assertEquals(1024, checkedResult);
```

Example 3:
```java
import org.julia.jni.swig.Julia4J;
import org.julia.jni.swig.SWIGTYPE_p_jl_value_t;
import org.julia.scripting.JuliaScriptEngineFactory;

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

// example from https://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/
```
### Prerequisites

For running: julia should be installed.

For building with binaries: julia, julia-devel, Java 8, swig, gcc, make.

### How to build
These sources doesn't include native binary libraries. But you can build them manually by [`swig/build.sh`](swig/build.sh) script.
Also see [swig/lib_src/Makefile](swig/lib_src/Makefile)

Next run `./gradlew build`

### TODO

* automate native binaries building. For now the only Mac OS library building hardcoded
* fix SWIG related class names like *SWIGTYPE_p_jl_value_t*
* implement convenient Java<->julia_JNI types converters
* implement all methods of scripting engine/factory instead of stubs
* implement direct access to functions and variable of Julia context to avoid code parsing per call
* ...
