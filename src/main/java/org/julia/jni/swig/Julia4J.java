/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.julia.jni.swig;

public class Julia4J {
  public static void jl_init() {
    Julia4JJNI.jl_init();
  }

  public static void jl_init_with_image(String julia_bindir, String image_relative_path) {
    Julia4JJNI.jl_init_with_image(julia_bindir, image_relative_path);
  }

  public static String jl_get_default_sysimg_path() {
    return Julia4JJNI.jl_get_default_sysimg_path();
  }

  public static int jl_is_initialized() {
    return Julia4JJNI.jl_is_initialized();
  }

  public static void jl_atexit_hook(int status) {
    Julia4JJNI.jl_atexit_hook(status);
  }

  public static void jl_exit(int status) {
    Julia4JJNI.jl_exit(status);
  }

  public static String jl_pathname_for_handle(SWIGTYPE_p_void handle) {
    return Julia4JJNI.jl_pathname_for_handle(SWIGTYPE_p_void.getCPtr(handle));
  }

  public static void jl_preload_sysimg_so(String fname) {
    Julia4JJNI.jl_preload_sysimg_so(fname);
  }

  public static void jl_set_sysimg_so(SWIGTYPE_p_void handle) {
    Julia4JJNI.jl_set_sysimg_so(SWIGTYPE_p_void.getCPtr(handle));
  }

  public static SWIGTYPE_p_ios_t jl_create_system_image() {
    long cPtr = Julia4JJNI.jl_create_system_image();
    return (cPtr == 0) ? null : new SWIGTYPE_p_ios_t(cPtr, false);
  }

  public static void jl_save_system_image(String fname) {
    Julia4JJNI.jl_save_system_image(fname);
  }

  public static void jl_restore_system_image(String fname) {
    Julia4JJNI.jl_restore_system_image(fname);
  }

  public static void jl_restore_system_image_data(String buf, long len) {
    Julia4JJNI.jl_restore_system_image_data(buf, len);
  }

  public static int jl_save_incremental(String fname, SWIGTYPE_p_jl_array_t worklist) {
    return Julia4JJNI.jl_save_incremental(fname, SWIGTYPE_p_jl_array_t.getCPtr(worklist));
  }

  public static SWIGTYPE_p_jl_value_t jl_restore_incremental(String fname, SWIGTYPE_p_jl_array_t depmods) {
    long cPtr = Julia4JJNI.jl_restore_incremental(fname, SWIGTYPE_p_jl_array_t.getCPtr(depmods));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_restore_incremental_from_buf(String buf, long sz, SWIGTYPE_p_jl_array_t depmods) {
    long cPtr = Julia4JJNI.jl_restore_incremental_from_buf(buf, sz, SWIGTYPE_p_jl_array_t.getCPtr(depmods));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_parse_input_line(String str, long len, String filename, long filename_len) {
    long cPtr = Julia4JJNI.jl_parse_input_line(str, len, filename, filename_len);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_parse_string(String str, long len, int pos0, int greedy) {
    long cPtr = Julia4JJNI.jl_parse_string(str, len, pos0, greedy);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_load_file_string(String text, long len, String filename, SWIGTYPE_p_jl_module_t inmodule) {
    long cPtr = Julia4JJNI.jl_load_file_string(text, len, filename, SWIGTYPE_p_jl_module_t.getCPtr(inmodule));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_expand(SWIGTYPE_p_jl_value_t expr, SWIGTYPE_p_jl_module_t inmodule) {
    long cPtr = Julia4JJNI.jl_expand(SWIGTYPE_p_jl_value_t.getCPtr(expr), SWIGTYPE_p_jl_module_t.getCPtr(inmodule));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_expand_stmt(SWIGTYPE_p_jl_value_t expr, SWIGTYPE_p_jl_module_t inmodule) {
    long cPtr = Julia4JJNI.jl_expand_stmt(SWIGTYPE_p_jl_value_t.getCPtr(expr), SWIGTYPE_p_jl_module_t.getCPtr(inmodule));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_eval_string(String str) {
    long cPtr = Julia4JJNI.jl_eval_string(str);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_new_bits(SWIGTYPE_p_jl_value_t bt, SWIGTYPE_p_void data) {
    long cPtr = Julia4JJNI.jl_new_bits(SWIGTYPE_p_jl_value_t.getCPtr(bt), SWIGTYPE_p_void.getCPtr(data));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_new_struct(SWIGTYPE_p_jl_datatype_t type) {
    long cPtr = Julia4JJNI.jl_new_struct(SWIGTYPE_p_jl_datatype_t.getCPtr(type));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_new_structv(SWIGTYPE_p_jl_datatype_t type, SWIGTYPE_p_p_jl_value_t args, long na) {
    long cPtr = Julia4JJNI.jl_new_structv(SWIGTYPE_p_jl_datatype_t.getCPtr(type), SWIGTYPE_p_p_jl_value_t.getCPtr(args), na);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_new_struct_uninit(SWIGTYPE_p_jl_datatype_t type) {
    long cPtr = Julia4JJNI.jl_new_struct_uninit(SWIGTYPE_p_jl_datatype_t.getCPtr(type));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_method_instance_t jl_new_method_instance_uninit() {
    long cPtr = Julia4JJNI.jl_new_method_instance_uninit();
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_method_instance_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_svec_t jl_svec(long n) {
    long cPtr = Julia4JJNI.jl_svec(n);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_svec_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_svec_t jl_svec1(SWIGTYPE_p_void a) {
    long cPtr = Julia4JJNI.jl_svec1(SWIGTYPE_p_void.getCPtr(a));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_svec_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_svec_t jl_svec2(SWIGTYPE_p_void a, SWIGTYPE_p_void b) {
    long cPtr = Julia4JJNI.jl_svec2(SWIGTYPE_p_void.getCPtr(a), SWIGTYPE_p_void.getCPtr(b));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_svec_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_svec_t jl_alloc_svec(long n) {
    long cPtr = Julia4JJNI.jl_alloc_svec(n);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_svec_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_svec_t jl_alloc_svec_uninit(long n) {
    long cPtr = Julia4JJNI.jl_alloc_svec_uninit(n);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_svec_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_svec_t jl_svec_copy(SWIGTYPE_p_jl_svec_t a) {
    long cPtr = Julia4JJNI.jl_svec_copy(SWIGTYPE_p_jl_svec_t.getCPtr(a));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_svec_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_svec_t jl_svec_fill(long n, SWIGTYPE_p_jl_value_t x) {
    long cPtr = Julia4JJNI.jl_svec_fill(n, SWIGTYPE_p_jl_value_t.getCPtr(x));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_svec_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_tupletype_fill(long n, SWIGTYPE_p_jl_value_t v) {
    long cPtr = Julia4JJNI.jl_tupletype_fill(n, SWIGTYPE_p_jl_value_t.getCPtr(v));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_sym_t jl_symbol(String str) {
    long cPtr = Julia4JJNI.jl_symbol(str);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_sym_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_sym_t jl_symbol_lookup(String str) {
    long cPtr = Julia4JJNI.jl_symbol_lookup(str);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_sym_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_sym_t jl_symbol_n(String str, long len) {
    long cPtr = Julia4JJNI.jl_symbol_n(str, len);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_sym_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_sym_t jl_gensym() {
    long cPtr = Julia4JJNI.jl_gensym();
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_sym_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_sym_t jl_tagged_gensym(String str, int len) {
    long cPtr = Julia4JJNI.jl_tagged_gensym(str, len);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_sym_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_sym_t jl_get_root_symbol() {
    long cPtr = Julia4JJNI.jl_get_root_symbol();
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_sym_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_generic_function_def(SWIGTYPE_p_jl_sym_t name, SWIGTYPE_p_jl_module_t module, SWIGTYPE_p_p_jl_value_t bp, SWIGTYPE_p_jl_value_t bp_owner, SWIGTYPE_p_jl_binding_t bnd) {
    long cPtr = Julia4JJNI.jl_generic_function_def(SWIGTYPE_p_jl_sym_t.getCPtr(name), SWIGTYPE_p_jl_module_t.getCPtr(module), SWIGTYPE_p_p_jl_value_t.getCPtr(bp), SWIGTYPE_p_jl_value_t.getCPtr(bp_owner), SWIGTYPE_p_jl_binding_t.getCPtr(bnd));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static void jl_method_def(SWIGTYPE_p_jl_svec_t argdata, SWIGTYPE_p_jl_code_info_t f, SWIGTYPE_p_jl_module_t module) {
    Julia4JJNI.jl_method_def(SWIGTYPE_p_jl_svec_t.getCPtr(argdata), SWIGTYPE_p_jl_code_info_t.getCPtr(f), SWIGTYPE_p_jl_module_t.getCPtr(module));
  }

  public static SWIGTYPE_p_jl_code_info_t jl_code_for_staged(SWIGTYPE_p_jl_method_instance_t linfo) {
    long cPtr = Julia4JJNI.jl_code_for_staged(SWIGTYPE_p_jl_method_instance_t.getCPtr(linfo));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_code_info_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_code_info_t jl_copy_code_info(SWIGTYPE_p_jl_code_info_t src) {
    long cPtr = Julia4JJNI.jl_copy_code_info(SWIGTYPE_p_jl_code_info_t.getCPtr(src));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_code_info_t(cPtr, false);
  }

  public static long jl_get_world_counter() {
    return Julia4JJNI.jl_get_world_counter();
  }

  public static SWIGTYPE_p_jl_function_t jl_get_kwsorter(SWIGTYPE_p_jl_value_t ty) {
    long cPtr = Julia4JJNI.jl_get_kwsorter(SWIGTYPE_p_jl_value_t.getCPtr(ty));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_function_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_bool(byte x) {
    long cPtr = Julia4JJNI.jl_box_bool(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_int8(byte x) {
    long cPtr = Julia4JJNI.jl_box_int8(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_uint8(short x) {
    long cPtr = Julia4JJNI.jl_box_uint8(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_int16(short x) {
    long cPtr = Julia4JJNI.jl_box_int16(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_uint16(int x) {
    long cPtr = Julia4JJNI.jl_box_uint16(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_int32(int x) {
    long cPtr = Julia4JJNI.jl_box_int32(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_uint32(long x) {
    long cPtr = Julia4JJNI.jl_box_uint32(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_char(long x) {
    long cPtr = Julia4JJNI.jl_box_char(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_int64(long x) {
    long cPtr = Julia4JJNI.jl_box_int64(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_uint64(java.math.BigInteger x) {
    long cPtr = Julia4JJNI.jl_box_uint64(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_float32(float x) {
    long cPtr = Julia4JJNI.jl_box_float32(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_float64(double x) {
    long cPtr = Julia4JJNI.jl_box_float64(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_voidpointer(SWIGTYPE_p_void x) {
    long cPtr = Julia4JJNI.jl_box_voidpointer(SWIGTYPE_p_void.getCPtr(x));
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_ssavalue(long x) {
    long cPtr = Julia4JJNI.jl_box_ssavalue(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static SWIGTYPE_p_jl_value_t jl_box_slotnumber(long x) {
    long cPtr = Julia4JJNI.jl_box_slotnumber(x);
    return (cPtr == 0) ? null : new SWIGTYPE_p_jl_value_t(cPtr, false);
  }

  public static byte jl_unbox_bool(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_bool(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static byte jl_unbox_int8(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_int8(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static short jl_unbox_uint8(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_uint8(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static short jl_unbox_int16(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_int16(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static int jl_unbox_uint16(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_uint16(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static int jl_unbox_int32(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_int32(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static long jl_unbox_uint32(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_uint32(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static long jl_unbox_int64(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_int64(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static java.math.BigInteger jl_unbox_uint64(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_uint64(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static float jl_unbox_float32(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_float32(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static double jl_unbox_float64(SWIGTYPE_p_jl_value_t v) {
    return Julia4JJNI.jl_unbox_float64(SWIGTYPE_p_jl_value_t.getCPtr(v));
  }

  public static SWIGTYPE_p_void jl_unbox_voidpointer(SWIGTYPE_p_jl_value_t v) {
    long cPtr = Julia4JJNI.jl_unbox_voidpointer(SWIGTYPE_p_jl_value_t.getCPtr(v));
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public static int jl_get_size(SWIGTYPE_p_jl_value_t val, SWIGTYPE_p_size_t pnt) {
    return Julia4JJNI.jl_get_size(SWIGTYPE_p_jl_value_t.getCPtr(val), SWIGTYPE_p_size_t.getCPtr(pnt));
  }

}
