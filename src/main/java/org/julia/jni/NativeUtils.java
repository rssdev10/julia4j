/*
 * Class NativeUtils is published under the The MIT License:
 *
 * Copyright (c) 2012 Adam Heinrich <adam@adamh.cz>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.julia.jni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple library class which helps with loading dynamic libraries stored in the
 * JAR archive. These libraries usually contain implementation of some methods in
 * native code (using JNI - Java Native Interface).
 *
 * @see <a href="http://adamheinrich.com/blog/2012/how-to-load-native-jni-library-from-jar">http://adamheinrich.com/blog/2012/how-to-load-native-jni-library-from-jar</a>
 * @see <a href="https://github.com/adamheinrich/native-utils">https://github.com/adamheinrich/native-utils</a>
 *
 */
public class NativeUtils {

    /**
     * The minimum length a prefix for a file has to have according to {@link File#createTempFile(String, String)}}.
     */
    private static final int MIN_PREFIX_LENGTH = 3;
    public static final String NATIVE_FOLDER_PATH_PREFIX = "nativeutils";
    public static final String JAVA_LIBRARY_PATH = "java.library.path";

    /**
     * Temporary directory which will contain the DLLs.
     */
    private static File temporaryDir;

    /**
     * Private constructor - this class will never be instanced
     */
    private NativeUtils() {
    }

    /**
     * Loads library from current JAR archive
     *
     * The file from JAR is copied into system temporary directory and then loaded. The temporary file is deleted after
     * exiting.
     * Method uses String as filename because the pathname is "abstract", not system-dependent.
     *
     * @param path The path of file inside JAR as absolute path (beginning with '/'), e.g. /package/File.ext
     * @throws IOException If temporary file creation or read/write operation fails
     * @throws IllegalArgumentException If source file (param path) does not exist
     * @throws IllegalArgumentException If the path is not absolute or if the filename is shorter than three characters
     * (restriction of {@link File#createTempFile(java.lang.String, java.lang.String)}).
     * @throws FileNotFoundException If the file could not be found inside the JAR.
     */
    public static void loadLibraryFromJar(String path, String libraryPaths) throws IOException {
        setupJavaLibraryPaths(libraryPaths);

        if (null == path || !path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }

        // Obtain filename from path
        String[] parts = path.split("/");
        String filename = (parts.length > 1) ? parts[parts.length - 1] : null;

        // Check if the filename is okay
        if (filename == null || filename.length() < MIN_PREFIX_LENGTH) {
            throw new IllegalArgumentException("The filename has to be at least 3 characters long.");
        }

        // Prepare temporary file
        if (temporaryDir == null) {
            temporaryDir = createTempDirectory(NATIVE_FOLDER_PATH_PREFIX);
            temporaryDir.deleteOnExit();
        }

        File temp = new File(temporaryDir, filename);

        try (InputStream is = NativeUtils.class.getResourceAsStream(path)) {
            Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            temp.delete();
            throw e;
        } catch (NullPointerException e) {
            temp.delete();
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        try {
            System.load(temp.getAbsolutePath());
        } catch (UnsatisfiedLinkError e) {
            throw new UnsatisfiedLinkError(
                    String.format("%s\njava.library.path=%s\n",
                    e.getMessage(), System.getProperty(JAVA_LIBRARY_PATH)));
        } finally {
            if (isPosixCompliant()) {
                // Assume POSIX compliant file system, can be deleted after loading
                temp.delete();
            } else {
                // Assume non-POSIX, and don't delete until last file descriptor closed
                temp.deleteOnExit();
            }
        }
    }

    public static void loadLibraryFromJar(String path) throws IOException {
        loadLibraryFromJar(path, "");
    }

    private static boolean isPosixCompliant() {
        try {
            return FileSystems.getDefault()
                    .supportedFileAttributeViews()
                    .contains("posix");
        } catch (FileSystemNotFoundException
                | ProviderNotFoundException
                | SecurityException e) {
            return false;
        }
    }

    private static File createTempDirectory(String prefix) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File generatedDir = new File(tempDir, prefix + System.nanoTime());

        if (!generatedDir.mkdir())
            throw new IOException("Failed to create temp directory " + generatedDir.getName());

        return generatedDir;
    }

    /**
     * Returns the resource path for a native library based on the current OS and architecture.
     * Example: /native/linux/x86_64/libfoo.so
     */
    public static String libnameToPlatform(String libname) {
        String osName = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();

        String osFolder;
        if (osName.contains("mac")) {
            osFolder = "darwin";
        } else if (osName.contains("linux")) {
            osFolder = "linux";
        } else if (osName.contains("windows")) {
            osFolder = "windows";
        } else {
            throw new IllegalArgumentException("Unsupported OS: " + osName);
        }

        String archFolder;
        if (arch.contains("aarch64") || arch.contains("arm64")) {
            archFolder = "arm64";
        } else if (arch.contains("arm")) {
            archFolder = "arm";
        } else if (arch.contains("x86_64") || arch.contains("amd64") || arch.contains("x64")) {
            archFolder = "x64";
        } else if (arch.matches("^(x86|i[3-6]86)$") || arch.contains("x86")) {
            archFolder = "x86";
        } else {
            throw new IllegalArgumentException("Unsupported architecture: " + arch);
        }

        String extension;
        if (osFolder.equals("darwin")) {
            extension = ".jnilib";
        } else if (osFolder.equals("linux")) {
            extension = ".so";
        } else if (osFolder.equals("windows")) {
            extension = ".dll";
        } else {
            throw new IllegalArgumentException("Unknown extension for OS: " + osFolder);
        }

        // On Windows, libraries are usually named without "lib" prefix
        String fileName = (osFolder.equals("windows") ? "" : "lib") + libname + extension;

        return "/native/" + osFolder + "/" + archFolder + "/" + fileName;
    }

    /**
     * Debug method
     *
     * @return list of names of loaded dynamic libraries
     */
    public static List<String> loadedLibraryNames() {
        try {
            final Field lib = ClassLoader.class.getDeclaredField("loadedLibraryNames");
            lib.setAccessible(true);
            Object list = lib.get(ClassLoader.getSystemClassLoader());
            if (list instanceof List<?>)
                return (List<String>) list;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static void setupJavaLibraryPaths(String externalPaths) {
        String javaLibraryPath = System.getProperty(JAVA_LIBRARY_PATH);
        String javaHome = System.getProperty("java.home");
//        if (!javaLibraryPath.contains(javaHome)) {
        String javaLibPath = javaHome + File.separator + "lib";
            final List<String> newSysPaths =
                    new ArrayList<>(List.of(javaLibraryPath.split(File.pathSeparator)));
            newSysPaths.add(javaLibPath);
            newSysPaths.add(javaLibPath + File.separator + "server");
            newSysPaths.addAll(List.of(externalPaths.split(File.pathSeparator)));

            System.setProperty(JAVA_LIBRARY_PATH,
                    newSysPaths.stream()
                            .filter(s -> !s.isEmpty())
                            .distinct()
                            .collect(Collectors.joining(File.pathSeparator))
            );
//        }
    }
}
