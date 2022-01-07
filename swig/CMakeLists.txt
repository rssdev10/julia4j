cmake_minimum_required(VERSION 3.0)
project(Julia4J VERSION 0.0.0.1 LANGUAGES C)

find_package(SWIG REQUIRED)
include(${SWIG_USE_FILE})
#find_package(Java COMPONENTS Development)
find_package(JNI REQUIRED)

include(./FindJULIA.cmake)

set_property(SOURCE julia4j.i PROPERTY C ON)
set_property(SOURCE julia4j.i PROPERTY COMPILE_OPTIONS
  -package org.julia.jni.swig)

swig_add_library(julia4j
  TYPE SHARED
    LANGUAGE java
  OUTPUT_DIR ${PROJECT_BINARY_DIR}/../src/main/java/org/julia/jni/swig
    SOURCES	julia4j.i)

set_target_properties(julia4j PROPERTIES
  SWIG_INCLUDE_DIRECTORIES ${PROJECT_SOURCE_DIR}/java
  SWIG_USE_TARGET_INCLUDE_DIRECTORIES ON
  POSITION_INDEPENDENT_CODE ON)
target_include_directories(julia4j PUBLIC ${JNI_INCLUDE_DIRS} ${Julia_INCLUDE_DIRS})
target_link_libraries(julia4j ${JNI_LIBRARIES} ${Julia_LIBRARY} ${Julia_LIBRARY_DIR})

# set(CMAKE_INSTALL_RPATH "@loader_path")

string(TOLOWER ${CMAKE_SYSTEM_NAME} OUT_OS_NAME)
install (TARGETS julia4j #${PROJECT_NAME}
       LIBRARY DESTINATION ${PROJECT_SOURCE_DIR}/../src/main/resources/native/64/${OUT_OS_NAME}/
)
