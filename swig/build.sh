JULIA_JAVA_PATH=`pwd`/../../src/main/java/org/julia/jni/swig
rm $JULIA_JAVA_PATH/*.java

cmake .
make

mv libjulia4j.jnilib ../src/main/resources/native/64/darwin/
