# Original FindJulia.cmake from https://github.com/QuantStack/xtensor-julia-cookiecutter/blob/master/%7B%7Bcookiecutter.github_project_name%7D%7D/cmake/FindJulia.cmake
# https://github.com/JuliaInterop/libcxxwrap-julia/blob/main/FindJulia.cmake

if(Julia_FOUND)
    return()
endif()

# Julia Executable
if(Julia_PREFIX)
    message(STATUS "Adding path ${Julia_PREFIX} to search path")
    list(APPEND CMAKE_PREFIX_PATH "${Julia_PREFIX}")
else()
    find_program(Julia_EXECUTABLE NAMES julia DOC "Julia executable")
    message(STATUS "Found Julia executable: ${Julia_EXECUTABLE}")
endif()

# Julia Version
if(Julia_EXECUTABLE)
    execute_process(
        COMMAND "${Julia_EXECUTABLE}" --startup-file=no --version
        OUTPUT_VARIABLE Julia_VERSION_STRING
        OUTPUT_STRIP_TRAILING_WHITESPACE
    )
else()
    find_file(Julia_VERSION_INCLUDE NAMES julia_version.h PATH_SUFFIXES include/julia)
    if(Julia_VERSION_INCLUDE)
        file(READ "${Julia_VERSION_INCLUDE}" Julia_VERSION_STRING)
        string(REGEX MATCH "JULIA_VERSION_STRING.*" Julia_VERSION_STRING "${Julia_VERSION_STRING}")
    endif()
endif()

string(REGEX REPLACE ".*([0-9]+\\.[0-9]+\\.[0-9]+).*" "\\1" Julia_VERSION_STRING "${Julia_VERSION_STRING}")
message(STATUS "Julia_VERSION_STRING: ${Julia_VERSION_STRING}")

# Julia Includes
set(JULIA_HOME_NAME "Sys.BINDIR")
if(Julia_VERSION_STRING VERSION_LESS "0.7.0")
    set(JULIA_HOME_NAME "JULIA_HOME")
else()
    set(USING_LIBDL "using Libdl")
endif()

if(DEFINED ENV{JULIA_INCLUDE_DIRS})
    set(Julia_INCLUDE_DIRS "$ENV{JULIA_INCLUDE_DIRS}" CACHE STRING "Location of Julia include files")
elseif(Julia_EXECUTABLE)
    execute_process(
        COMMAND "${Julia_EXECUTABLE}" --startup-file=no -E
            "julia_include_dir = joinpath(match(r\"(.*)(bin)\",${JULIA_HOME_NAME}).captures[1],\"include\",\"julia\")
            if !isdir(julia_include_dir)
                julia_base_dir_aux = splitdir(splitdir(${JULIA_HOME_NAME})[1])[1]
                julia_include_dir = joinpath(julia_base_dir_aux, \"usr\", \"include\")
                julia_include_dir *= \";\" * joinpath(julia_base_dir_aux, \"src\", \"support\")
                julia_include_dir *= \";\" * joinpath(julia_base_dir_aux, \"src\")
            end
            julia_include_dir"
        OUTPUT_VARIABLE Julia_INCLUDE_DIRS
        OUTPUT_STRIP_TRAILING_WHITESPACE
    )
    string(REGEX REPLACE "\"" "" Julia_INCLUDE_DIRS "${Julia_INCLUDE_DIRS}")
    string(REGEX REPLACE "\n" "" Julia_INCLUDE_DIRS "${Julia_INCLUDE_DIRS}")
    set(Julia_INCLUDE_DIRS "${Julia_INCLUDE_DIRS}" CACHE PATH "Location of Julia include files")
elseif(Julia_PREFIX)
    set(Julia_INCLUDE_DIRS "${Julia_PREFIX}/include/julia")
endif()
if(DEFINED ENV{includedir})
    set(Julia_INCLUDE_DIRS "${Julia_INCLUDE_DIRS};$ENV{includedir}")
endif()
message(STATUS "Julia_INCLUDE_DIRS:   ${Julia_INCLUDE_DIRS}")

# Julia Libraries
if(WIN32)
    list(APPEND CMAKE_FIND_LIBRARY_SUFFIXES .a .dll)
endif()

if(Julia_EXECUTABLE)
    execute_process(
        COMMAND "${Julia_EXECUTABLE}" --startup-file=no -E
            "${USING_LIBDL}\nabspath(Libdl.dlpath((ccall(:jl_is_debugbuild, Cint, ()) != 0) ? \"libjulia-debug\" : \"libjulia\"))"
        OUTPUT_VARIABLE Julia_LIBRARY
        OUTPUT_STRIP_TRAILING_WHITESPACE
    )
    string(REGEX REPLACE "\"" "" Julia_LIBRARY "${Julia_LIBRARY}")
    string(REGEX REPLACE "\n" "" Julia_LIBRARY "${Julia_LIBRARY}")
    string(STRIP "${Julia_LIBRARY}" Julia_LIBRARY)

    if(WIN32)
        get_filename_component(Julia_LIBRARY_DIR "${Julia_LIBRARY}" DIRECTORY)
        get_filename_component(Julia_LIBRARY_DIR "${Julia_LIBRARY_DIR}" DIRECTORY)
        find_library(win_Julia_LIBRARY
            NAMES libjulia.dll.a
            PATHS "${Julia_LIBRARY_DIR}/lib"
            NO_DEFAULT_PATH
        )
        set(Julia_LIBRARY "${win_Julia_LIBRARY}")
    endif()

    set(Julia_LIBRARY "${Julia_LIBRARY}" CACHE PATH "Julia library")
else()
    find_library(Julia_LIBRARY
        NAMES libjulia.${Julia_VERSION_STRING}.dylib julia libjulia.dll.a libjulia
        PATHS ENV JULIA_LIBRARY_PATH
        PATH_SUFFIXES lib
        CMAKE_FIND_ROOT_PATH_BOTH
    )
endif()

get_filename_component(Julia_LIBRARY_DIR "${Julia_LIBRARY}" DIRECTORY)
message(STATUS "Julia_LIBRARY_DIR:    ${Julia_LIBRARY_DIR}")
message(STATUS "Julia_LIBRARY:        ${Julia_LIBRARY}")

# JULIA_HOME
if(Julia_EXECUTABLE)
    execute_process(
        COMMAND "${Julia_EXECUTABLE}" --startup-file=no -E "${JULIA_HOME_NAME}"
        OUTPUT_VARIABLE JULIA_HOME
        OUTPUT_STRIP_TRAILING_WHITESPACE
    )
    string(REGEX REPLACE "\"" "" JULIA_HOME "${JULIA_HOME}")
    string(REGEX REPLACE "\n" "" JULIA_HOME "${JULIA_HOME}")
    message(STATUS "JULIA_HOME:           ${JULIA_HOME}")

    # libLLVM version
    execute_process(
        COMMAND "${Julia_EXECUTABLE}" --startup-file=no -E "Base.libllvm_version"
        OUTPUT_VARIABLE Julia_LLVM_VERSION
        OUTPUT_STRIP_TRAILING_WHITESPACE
    )
    string(REGEX REPLACE "\"" "" Julia_LLVM_VERSION "${Julia_LLVM_VERSION}")
    string(REGEX REPLACE "\n" "" Julia_LLVM_VERSION "${Julia_LLVM_VERSION}")
    message(STATUS "Julia_LLVM_VERSION:   ${Julia_LLVM_VERSION}")
endif()

# Check for Existence of Headers
find_path(Julia_MAIN_HEADER NAMES julia.h HINTS ${Julia_INCLUDE_DIRS})

# Determine if we are on 32 or 64 bit
if(Julia_EXECUTABLE)
    execute_process(
        COMMAND "${Julia_EXECUTABLE}" --startup-file=no -E "Sys.WORD_SIZE"
        OUTPUT_VARIABLE Julia_WORD_SIZE
        OUTPUT_STRIP_TRAILING_WHITESPACE
    )
    string(REGEX REPLACE "\n" "" Julia_WORD_SIZE "${Julia_WORD_SIZE}")
    message(STATUS "Julia_WORD_SIZE:      ${Julia_WORD_SIZE}")
endif()

if(DEFINED ENV{target} AND "$ENV{target}" MATCHES "^i686.*")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -msse -msse2")
endif()

# FindPackage Boilerplate
include(FindPackageHandleStandardArgs)
find_package_handle_standard_args(Julia
    REQUIRED_VARS Julia_LIBRARY Julia_LIBRARY_DIR Julia_INCLUDE_DIRS Julia_MAIN_HEADER
    VERSION_VAR Julia_VERSION_STRING
    FAIL_MESSAGE "Julia not found"
)

