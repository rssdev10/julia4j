/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.1
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.julia.jni.swig;

public class Julia4JJNI {
  public final static native void jl_init();
  public final static native void jl_init_with_image(String jarg1, String jarg2);
  public final static native String jl_get_default_sysimg_path();
  public final static native int jl_is_initialized();
  public final static native void jl_atexit_hook(int jarg1);
  public final static native void jl_exit(int jarg1);
  public final static native String jl_pathname_for_handle(long jarg1);
  public final static native void jl_preload_sysimg_so(String jarg1);
  public final static native void jl_set_sysimg_so(long jarg1);
  public final static native long jl_create_system_image();
  public final static native void jl_save_system_image(String jarg1);
  public final static native void jl_restore_system_image(String jarg1);
  public final static native void jl_restore_system_image_data(String jarg1, long jarg2);
  public final static native int jl_save_incremental(String jarg1, long jarg2);
  public final static native long jl_restore_incremental(String jarg1, long jarg2);
  public final static native long jl_restore_incremental_from_buf(String jarg1, long jarg2, long jarg3);
  public final static native long jl_parse_input_line(String jarg1, long jarg2, String jarg3, long jarg4);
  public final static native long jl_parse_string(String jarg1, long jarg2, int jarg3, int jarg4);
  public final static native long jl_load_file_string(String jarg1, long jarg2, String jarg3, long jarg4);
  public final static native long jl_expand(long jarg1, long jarg2);
  public final static native long jl_expand_stmt(long jarg1, long jarg2);
  public final static native long jl_eval_string(String jarg1);
  public final static native long jl_new_bits(long jarg1, long jarg2);
  public final static native long jl_new_struct(long jarg1);
  public final static native long jl_new_structv(long jarg1, long jarg2, long jarg3);
  public final static native long jl_new_struct_uninit(long jarg1);
  public final static native long jl_new_method_instance_uninit();
  public final static native long jl_svec(long jarg1);
  public final static native long jl_svec1(long jarg1);
  public final static native long jl_svec2(long jarg1, long jarg2);
  public final static native long jl_alloc_svec(long jarg1);
  public final static native long jl_alloc_svec_uninit(long jarg1);
  public final static native long jl_svec_copy(long jarg1);
  public final static native long jl_svec_fill(long jarg1, long jarg2);
  public final static native long jl_tupletype_fill(long jarg1, long jarg2);
  public final static native long jl_symbol(String jarg1);
  public final static native long jl_symbol_lookup(String jarg1);
  public final static native long jl_symbol_n(String jarg1, long jarg2);
  public final static native long jl_gensym();
  public final static native long jl_tagged_gensym(String jarg1, int jarg2);
  public final static native long jl_get_root_symbol();
  public final static native long jl_generic_function_def(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5);
  public final static native void jl_method_def(long jarg1, long jarg2, long jarg3);
  public final static native long jl_code_for_staged(long jarg1);
  public final static native long jl_copy_code_info(long jarg1);
  public final static native long jl_get_world_counter();
  public final static native long jl_get_kwsorter(long jarg1);
  public final static native long jl_box_bool(byte jarg1);
  public final static native long jl_box_int8(byte jarg1);
  public final static native long jl_box_uint8(short jarg1);
  public final static native long jl_box_int16(short jarg1);
  public final static native long jl_box_uint16(int jarg1);
  public final static native long jl_box_int32(int jarg1);
  public final static native long jl_box_uint32(long jarg1);
  public final static native long jl_box_char(long jarg1);
  public final static native long jl_box_int64(long jarg1);
  public final static native long jl_box_uint64(java.math.BigInteger jarg1);
  public final static native long jl_box_float32(float jarg1);
  public final static native long jl_box_float64(double jarg1);
  public final static native long jl_box_voidpointer(long jarg1);
  public final static native long jl_box_ssavalue(long jarg1);
  public final static native long jl_box_slotnumber(long jarg1);
  public final static native byte jl_unbox_bool(long jarg1);
  public final static native byte jl_unbox_int8(long jarg1);
  public final static native short jl_unbox_uint8(long jarg1);
  public final static native short jl_unbox_int16(long jarg1);
  public final static native int jl_unbox_uint16(long jarg1);
  public final static native int jl_unbox_int32(long jarg1);
  public final static native long jl_unbox_uint32(long jarg1);
  public final static native long jl_unbox_int64(long jarg1);
  public final static native java.math.BigInteger jl_unbox_uint64(long jarg1);
  public final static native float jl_unbox_float32(long jarg1);
  public final static native double jl_unbox_float64(long jarg1);
  public final static native long jl_unbox_voidpointer(long jarg1);
  public final static native int jl_get_size(long jarg1, long jarg2);
}
