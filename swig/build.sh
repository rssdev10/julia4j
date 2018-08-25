cd lib_src

JULIA_JAVA_PATH=`pwd`/../../src/main/java/org/julia/jni/swig
rm $JULIA_JAVA_PATH/*.java

JL_INCLUDE=`julia -e 'print(joinpath(Sys.BINDIR, "../include", "julia"))'`

swig -java -package org.julia.jni.swig -outdir $JULIA_JAVA_PATH -I$JL_INCLUDE -outcurrentdir ../julia4j.i
make

mv libjulia4j.dylib ../../src/main/resources/native/64/darwin/
rm *.o

cd ..
