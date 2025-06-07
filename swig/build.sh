JULIA_JAVA_PATH=`pwd`/../src/main/java/org/julia/jni/swig
rm $JULIA_JAVA_PATH/*.java

# if [ -z "$JAVA_HOME" ]; then
#   IFS=' = '
#   # java -XshowSettings:properties -version 2>&1 | findstr "java.home" # winver
#   JAVA_PROP=`java -XshowSettings:properties -version 2>&1 > /dev/null | grep 'java.home'`
#   read -a strarr <<< "$JAVA_PROP"
#   export JAVA_HOME="${strarr[1]}"
# # export JAVA_INCLUDE_PATH="$JAVA_HOME/include"
#   export JAVA_JVM_LIBRARY="$JAVA_HOME/lib"
# fi

echo $JAVA_HOME
#echo $JAVA_INCLUDE_PATH

# cmake . && \
# make && \
# mv libjulia4j.jnilib ../src/main/resources/native/64/darwin/

# export SWIG_LIB="$(brew --prefix swig)/share/swig/$(swig -version | awk '/Version/{print $NF}')"
# export PATH=$PATH:$SWIG_LIB
# cmake . && make clean && make install

cmake --build . --target clean
rm -rf CMakeFiles CMakeCache.txt cmake_install.cmake Makefile

cmake .
cmake --build . --config Release -v
cmake --install . --config Release
