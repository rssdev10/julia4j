 /* julia4j.i */
 %module Julia4J
 %include "stdint.i"

 %{
/* Put header files here or function declarations like below */
 #include "julia.h"

char * jl_unbox_charpointer(jl_value_t *v) {
     return (char *)jl_unbox_voidpointer(v);
 }

 %}

extern void jl_init(void);
extern void jl_init_with_image(const char *julia_bindir,
                                     const char *image_relative_path);
extern const char *jl_get_default_sysimg_path(void);
extern int jl_is_initialized(void);
extern void jl_atexit_hook(int status);
extern void jl_exit(int status);
extern const char *jl_pathname_for_handle(void *handle);

extern void jl_preload_sysimg_so(const char *fname);
extern void jl_set_sysimg_so(void *handle);
extern ios_t *jl_create_system_image(void *);
extern void jl_save_system_image(const char *fname);
extern void jl_restore_system_image(const char *fname);
extern void jl_restore_system_image_data(const char *buf, size_t len);
extern int jl_save_incremental(const char *fname, jl_array_t *worklist);
extern jl_value_t *jl_restore_incremental(const char *fname, jl_array_t *depmods);
extern jl_value_t *jl_restore_incremental_from_buf(const char *buf, size_t sz, jl_array_t *depmods);

// front end interface
extern jl_value_t *jl_parse_input_line(const char *str, size_t len,
                                             const char *filename, size_t filename_len);
extern jl_value_t *jl_parse_string(const char *str, size_t len,
                                         int pos0, int greedy);
extern jl_value_t *jl_load_file_string(const char *text, size_t len,
                                             char *filename, jl_module_t *inmodule);
extern jl_value_t *jl_expand(jl_value_t *expr, jl_module_t *inmodule);
extern jl_value_t *jl_expand_stmt(jl_value_t *expr, jl_module_t *inmodule);
extern jl_value_t *jl_eval_string(const char *str);


// constructors
extern jl_value_t *jl_new_bits(jl_value_t *bt, void *data);
extern jl_value_t *jl_new_struct(jl_datatype_t *type, ...);
extern jl_value_t *jl_new_structv(jl_datatype_t *type, jl_value_t **args,
                                        uint32_t na);
extern jl_value_t *jl_new_struct_uninit(jl_datatype_t *type);
extern jl_method_instance_t *jl_new_method_instance_uninit(void);
extern jl_svec_t *jl_svec(size_t n, ...) ;
extern jl_svec_t *jl_svec1(void *a);
extern jl_svec_t *jl_svec2(void *a, void *b);
extern jl_svec_t *jl_alloc_svec(size_t n);
extern jl_svec_t *jl_alloc_svec_uninit(size_t n);
extern jl_svec_t *jl_svec_copy(jl_svec_t *a);
extern jl_svec_t *jl_svec_fill(size_t n, jl_value_t *x);
extern jl_value_t *jl_tupletype_fill(size_t n, jl_value_t *v);
extern jl_sym_t *jl_symbol(const char *str) ;
extern jl_sym_t *jl_symbol_lookup(const char *str) ;
extern jl_sym_t *jl_symbol_n(const char *str, size_t len) ;
extern jl_sym_t *jl_gensym(void);
extern jl_sym_t *jl_tagged_gensym(const char *str, int32_t len);
extern jl_sym_t *jl_get_root_symbol(void);
// extern jl_value_t *jl_generic_function_def(jl_sym_t *name,
//                                                  jl_module_t *module,
//                                                  std::atomic<jl_value_t*> *bp, jl_value_t *bp_owner,
//                                                  jl_binding_t *bnd);
// extern jl_method_t *jl_method_def(jl_svec_t *argdata, jl_methtable_t *mt, jl_code_info_t *f, jl_module_t *module);
extern jl_code_info_t *jl_code_for_staged(jl_method_instance_t *linfo);
extern jl_code_info_t *jl_copy_code_info(jl_code_info_t *src);
extern size_t jl_get_world_counter(void);
extern jl_function_t *jl_get_kwsorter(jl_value_t *ty);
extern jl_value_t *jl_box_bool(int8_t x) ;
extern jl_value_t *jl_box_int8(int8_t x) ;
extern jl_value_t *jl_box_uint8(uint8_t x) ;
extern jl_value_t *jl_box_int16(int16_t x);
extern jl_value_t *jl_box_uint16(uint16_t x);
extern jl_value_t *jl_box_int32(int32_t x);
extern jl_value_t *jl_box_uint32(uint32_t x);
extern jl_value_t *jl_box_char(uint32_t x);
extern jl_value_t *jl_box_int64(int64_t x);
extern jl_value_t *jl_box_uint64(uint64_t x);
extern jl_value_t *jl_box_float32(float x);
extern jl_value_t *jl_box_float64(double x);
extern jl_value_t *jl_box_voidpointer(void *x);
extern jl_value_t *jl_box_ssavalue(size_t x);
extern jl_value_t *jl_box_slotnumber(size_t x);

extern int8_t jl_unbox_bool(jl_value_t *v) ;
extern int8_t jl_unbox_int8(jl_value_t *v) ;
extern uint8_t jl_unbox_uint8(jl_value_t *v) ;
extern int16_t jl_unbox_int16(jl_value_t *v) ;
extern uint16_t jl_unbox_uint16(jl_value_t *v) ;
extern int32_t jl_unbox_int32(jl_value_t *v) ;
extern uint32_t jl_unbox_uint32(jl_value_t *v) ;
extern int64_t jl_unbox_int64(jl_value_t *v) ;
extern uint64_t jl_unbox_uint64(jl_value_t *v) ;
extern float jl_unbox_float32(jl_value_t *v) ;
extern double jl_unbox_float64(jl_value_t *v) ;
extern void *jl_unbox_voidpointer(jl_value_t *v) ;

extern int jl_get_size(jl_value_t *val, size_t *pnt);


// custom helpers
extern char *jl_unbox_charpointer(jl_value_t *v) ;
