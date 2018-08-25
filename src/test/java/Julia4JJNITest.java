import org.julia.jni.NativeUtils;
import org.julia.jni.swig.Julia4J;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Test of JNI Julia binding
 *
 *
 * Created by rss on 25/08/2018
 */
public class Julia4JJNITest {
    static {
        try {
            NativeUtils.loadLibraryFromJar(NativeUtils.libnameToPlatform("libjulia4j"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void juliaShouldWork() {
        Julia4J.jl_init();
        Julia4J.jl_eval_string("dump(:(1 + 2x^2))");
        Julia4J.jl_atexit_hook(0);
    }

}
