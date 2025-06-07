cmake_minimum_required(VERSION 3.18)
project(Julia4J VERSION 0.0.0.1 LANGUAGES C)

# Add MSVC fixes here
if(MSVC)
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} /std:clatest /experimental:c11atomics")
    add_definitions(
        -D_CRT_SECURE_NO_WARNINGS
        -D_ARM64_WORKAROUND_
    )
endif()

find_package(SWIG REQUIRED)
include(${SWIG_USE_FILE})

# find_package(JNI REQUIRED) # sometimes it gives wrong directories

# Detect current Java installation
find_package(Java COMPONENTS Development REQUIRED)
execute_process(
  COMMAND ${Java_JAVA_EXECUTABLE} -XshowSettings:properties -version
  ERROR_VARIABLE java_settings
  OUTPUT_STRIP_TRAILING_WHITESPACE
)

# Extract java.home from Java output
string(REGEX MATCH "java.home = ([^\n]+)" _ ${java_settings})
if(CMAKE_MATCH_1)
    set(JAVA_HOME ${CMAKE_MATCH_1})
    message(STATUS "Detected JAVA_HOME: ${JAVA_HOME}")
    
    # Set platform-specific include paths
    if(APPLE)
        set(JNI_INCLUDE_DIRS "${JAVA_HOME}/include" "${JAVA_HOME}/include/darwin")
    elseif(WIN32)
        set(JNI_INCLUDE_DIRS "${JAVA_HOME}/include" "${JAVA_HOME}/include/win32")
    else()  # Linux/Unix
        set(JNI_INCLUDE_DIRS "${JAVA_HOME}/include" "${JAVA_HOME}/include/linux")
    endif()
else()
    message(FATAL_ERROR "Failed to detect Java home directory")
endif()

include_directories(SYSTEM ${JNI_INCLUDE_DIRS})
message(STATUS "JNI include paths: ${JNI_INCLUDE_DIRS}")

include(./FindJULIA.cmake)

set_source_files_properties(julia4j.i PROPERTIES C ON)
set_source_files_properties(julia4j.i PROPERTIES COMPILE_OPTIONS "-package;org.julia.jni.swig")

swig_add_library(julia4j
  TYPE SHARED
  LANGUAGE java
  OUTPUT_DIR "${PROJECT_BINARY_DIR}/../src/main/java/org/julia/jni/swig"
  SOURCES julia4j.i
)

# Get SWIG's standard include directory
execute_process(
  COMMAND ${SWIG_EXECUTABLE} -swiglib
  OUTPUT_VARIABLE SWIG_DIR
  OUTPUT_STRIP_TRAILING_WHITESPACE
)

set_target_properties(julia4j PROPERTIES
  SWIG_INCLUDE_DIRECTORIES "${SWIG_DIR};${SWIG_DIR}/java;${PROJECT_SOURCE_DIR}/java"
  SWIG_USE_TARGET_INCLUDE_DIRECTORIES ON
  POSITION_INDEPENDENT_CODE ON
)

target_include_directories(julia4j
  PUBLIC
    ${JNI_INCLUDE_DIRS}
    ${Julia_INCLUDE_DIRS}
)

target_link_libraries(julia4j
  PRIVATE
    ${JNI_LIBRARIES}
    ${Julia_LIBRARY}
)

string(TOLOWER "${CMAKE_SYSTEM_NAME}" OUT_OS_NAME)

cmake_policy(SET CMP0177 NEW)

# Determine OS folder name
if(APPLE)
  set(OS_FOLDER "darwin")
elseif(WIN32)
  set(OS_FOLDER "windows")
elseif(UNIX)
  set(OS_FOLDER "linux")
else()
  message(FATAL_ERROR "Unsupported OS: ${CMAKE_SYSTEM_NAME}")
endif()

message(STATUS "CMAKE_SYSTEM_PROCESSOR: ${CMAKE_SYSTEM_PROCESSOR}")
message(STATUS "CMAKE_HOST_SYSTEM_PROCESSOR: ${CMAKE_HOST_SYSTEM_PROCESSOR}")

# Use host processor for native builds
string(TOLOWER "${CMAKE_SYSTEM_PROCESSOR}" ARCH_LOWER)
if(ARCH_LOWER MATCHES "^(aarch64|arm64)$")
  set(ARCH_FOLDER "arm64")
elseif(ARCH_LOWER MATCHES "^arm")
  set(ARCH_FOLDER "arm")
elseif(ARCH_LOWER MATCHES "^(x86_64|amd64)$")
  set(ARCH_FOLDER "x64")
elseif(ARCH_LOWER MATCHES "^(x86|i[3-6]86)$")
  set(ARCH_FOLDER "x86")
else()
  message(FATAL_ERROR "Unsupported architecture: ${ARCH_LOWER}")
endif()

set(NATIVE_DEST "${PROJECT_SOURCE_DIR}/../src/main/resources/native/${OS_FOLDER}/${ARCH_FOLDER}/")

if(WIN32)
  install(
    TARGETS julia4j
    RUNTIME DESTINATION "${NATIVE_DEST}"
    ARCHIVE DESTINATION "${NATIVE_DEST}"
  )
else()
  install(
    TARGETS julia4j
    LIBRARY DESTINATION "${NATIVE_DEST}"
    ARCHIVE DESTINATION "${NATIVE_DEST}"
  )
endif()
