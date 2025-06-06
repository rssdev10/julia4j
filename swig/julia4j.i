 /* julia4j.i */
 %module Julia4J
 %include "stdint.i"

 %{
/* Put header files here or function declarations like below */
 #include "julia.h"

char * jl_unbox_string(jl_value_t *v) {
    return jl_string_data((char *)(v));
}

void jl_show(jl_value_t *v) {
   jl_static_show(jl_stdout_stream(), v);
};

 %}


extern const char *jl_get_libdir(void);
// extern void julia_init(JL_IMAGE_SEARCH rel);
extern void jl_init(void);
extern void jl_init_with_image(const char *julia_bindir,
                                     const char *image_path);
extern const char *jl_get_default_sysimg_path(void);
extern int jl_is_initialized(void);
extern void jl_atexit_hook(int status);
// extern void jl_postoutput_hook(void);
extern void  jl_exit(int status);
// extern void  jl_raise(int signo);
extern const char *jl_pathname_for_handle(void *handle);
extern jl_gcframe_t **jl_adopt_thread(void);

// extern int jl_deserialize_verify_header(ios_t *s);
extern void jl_preload_sysimg_so(const char *fname);
extern void jl_set_sysimg_so(void *handle);
extern void jl_create_system_image(void **, jl_array_t *worklist, bool_t emit_split, ios_t **s, ios_t **z, jl_array_t **udeps, int64_t *srctextpos);
extern void jl_restore_system_image(const char *fname);
extern void jl_restore_system_image_data(const char *buf, size_t len);

// extern void jl_set_newly_inferred(jl_value_t *newly_inferred);
// extern void jl_push_newly_inferred(jl_value_t *ci);
extern void jl_write_compiler_output(void);

// parsing
extern jl_value_t *jl_parse_all(const char *text, size_t text_len,
                                      const char *filename, size_t filename_len, size_t lineno);
extern jl_value_t *jl_parse_string(const char *text, size_t text_len,
                                         int offset, int greedy);
// lowering
extern jl_value_t *jl_expand(jl_value_t *expr, jl_module_t *inmodule);
extern jl_value_t *jl_expand_with_loc(jl_value_t *expr, jl_module_t *inmodule,
                                            const char *file, int line);
extern jl_value_t *jl_expand_with_loc_warn(jl_value_t *expr, jl_module_t *inmodule,
                                                 const char *file, int line);
// extern jl_value_t *jl_expand_in_world(jl_value_t *expr, jl_module_t *inmodule,
//                                             const char *file, int line, size_t world);
extern jl_value_t *jl_expand_stmt(jl_value_t *expr, jl_module_t *inmodule);
extern jl_value_t *jl_expand_stmt_with_loc(jl_value_t *expr, jl_module_t *inmodule,
                                                 const char *file, int line);


 jl_libhandle jl_load_dynamic_library(const char *fname, unsigned flags, int throw_err);
 jl_libhandle jl_dlopen(const char *filename, unsigned flags) ;
 int jl_dlclose(jl_libhandle handle) ;
 int jl_dlsym(jl_libhandle handle, const char *symbol, void ** value, int throw_err) ;

// evaluation
 jl_value_t *jl_toplevel_eval(jl_module_t *m, jl_value_t *v);
 jl_value_t *jl_toplevel_eval_in(jl_module_t *m, jl_value_t *ex);
// code loading (parsing + evaluation)
 jl_value_t *jl_eval_string(const char *str); // embedding interface
 jl_value_t *jl_load_file_string(const char *text, size_t len,
                                             char *filename, jl_module_t *module);
 jl_value_t *jl_load(jl_module_t *module, const char *fname);

 jl_module_t *jl_base_relative_to(jl_module_t *m );

// tracing
 void jl_register_newmeth_tracer(void (*callback)(jl_method_t *tracee));

// AST access
 jl_value_t *jl_copy_ast(jl_value_t *expr );

// IR representation
 jl_array_t *jl_compress_ir(jl_method_t *m, jl_code_info_t *code);
 jl_code_info_t *jl_uncompress_ir(jl_method_t *m, jl_code_instance_t *metadata, jl_array_t *data);

 jl_value_t *jl_compress_argnames(jl_array_t *syms);
 jl_array_t *jl_uncompress_argnames(jl_value_t *syms);
 jl_value_t *jl_uncompress_argname_n(jl_value_t *syms, size_t i);


 int jl_is_operator(char *sym);
 int jl_is_unary_operator(char *sym);
 int jl_is_unary_and_binary_operator(char *sym);
//  int jl_is_syntactic_operator(char *sym);
 int jl_operator_precedence(char *sym);


// calling into julia ---------------------------------------------------------

extern jl_value_t *jl_apply_generic(jl_value_t *F, jl_value_t **args, uint32_t nargs);
extern jl_value_t *jl_invoke(jl_value_t *F, jl_value_t **args, uint32_t nargs, jl_method_instance_t *meth);
extern int32_t jl_invoke_api(jl_code_instance_t *linfo);

extern jl_value_t *jl_call(jl_function_t *f , jl_value_t **args, uint32_t nargs);
extern jl_value_t *jl_call0(jl_function_t *f );
extern jl_value_t *jl_call1(jl_function_t *f , jl_value_t *a );
extern jl_value_t *jl_call2(jl_function_t *f , jl_value_t *a , jl_value_t *b );
extern jl_value_t *jl_call3(jl_function_t *f , jl_value_t *a ,
                                  jl_value_t *b , jl_value_t *c );

// interfacing with Task runtime
// extern void jl_yield(void);

// async signal handling ------------------------------------------------------

extern void jl_install_sigint_handler(void);
extern void jl_sigatomic_begin(void);
extern void jl_sigatomic_end(void);


// type constructors
extern jl_typename_t *jl_new_typename_in(jl_sym_t *name, jl_module_t *inmodule, int abstract, int mutabl);
extern jl_tvar_t *jl_new_typevar(jl_sym_t *name, jl_value_t *lb, jl_value_t *ub);
extern jl_value_t *jl_instantiate_unionall(jl_unionall_t *u, jl_value_t *p);
extern jl_value_t *jl_apply_type(jl_value_t *tc, jl_value_t **params, size_t n);
extern jl_value_t *jl_apply_type1(jl_value_t *tc, jl_value_t *p1);
extern jl_value_t *jl_apply_type2(jl_value_t *tc, jl_value_t *p1, jl_value_t *p2);
// extern jl_datatype_t *jl_apply_modify_type(jl_value_t *dt);
extern jl_datatype_t *jl_apply_cmpswap_type(jl_value_t *dt);
extern jl_value_t *jl_apply_tuple_type(jl_svec_t *params, int check); // if uncertain, set check=1
extern jl_value_t *jl_apply_tuple_type_v(jl_value_t **p, size_t np);
extern jl_datatype_t *jl_new_datatype(jl_sym_t *name,
                                            jl_module_t *module,
                                            jl_datatype_t *super,
                                            jl_svec_t *parameters,
                                            jl_svec_t *fnames,
                                            jl_svec_t *ftypes,
                                            jl_svec_t *fattrs,
                                            int abstract, int mutabl,
                                            int ninitialized);
extern jl_datatype_t *jl_new_primitivetype(jl_value_t *name,
                                                 jl_module_t *module,
                                                 jl_datatype_t *super,
                                                 jl_svec_t *parameters, size_t nbits);

// constructors
extern jl_value_t *jl_new_bits(jl_value_t *bt, const void *src);
extern jl_value_t *jl_atomic_new_bits(jl_value_t *dt, const char *src);
extern void jl_atomic_store_bits(char *dst, const jl_value_t *src, int nb);
extern jl_value_t *jl_atomic_swap_bits(jl_value_t *dt, char *dst, const jl_value_t *src, int nb);
extern int jl_atomic_bool_cmpswap_bits(char *dst, const jl_value_t *expected, const jl_value_t *src, int nb);
extern jl_value_t *jl_atomic_cmpswap_bits(jl_datatype_t *dt, jl_datatype_t *rettype, char *dst, const jl_value_t *expected, const jl_value_t *src, int nb);
extern jl_value_t *jl_new_struct(jl_datatype_t *type, ...);
extern jl_value_t *jl_new_structv(jl_datatype_t *type, jl_value_t **args, uint32_t na);
extern jl_value_t *jl_new_structt(jl_datatype_t *type, jl_value_t *tup);
extern jl_value_t *jl_new_struct_uninit(jl_datatype_t *type);
extern jl_method_instance_t *jl_new_method_instance_uninit(void);
extern jl_svec_t *jl_svec(size_t n, ...) ;
extern jl_svec_t *jl_svec1(void *a);
extern jl_svec_t *jl_svec2(void *a, void *b);
extern jl_svec_t *jl_alloc_svec(size_t n);
extern jl_svec_t *jl_alloc_svec_uninit(size_t n);
extern jl_svec_t *jl_svec_copy(jl_svec_t *a);
extern jl_svec_t *jl_svec_fill(size_t n, jl_value_t *x);
extern jl_sym_t *jl_symbol(const char *str) ;
extern jl_sym_t *jl_symbol_lookup(const char *str) ;
extern jl_sym_t *jl_symbol_n(const char *str, size_t len) ;
extern jl_sym_t *jl_gensym(void);
extern jl_sym_t *jl_tagged_gensym(const char *str, size_t len);
extern jl_sym_t *jl_get_root_symbol(void);
// extern jl_value_t *jl_generic_function_def(jl_sym_t *name,
//                                                  jl_module_t *module,
//                                                  jl_value_t *bp, jl_value_t *bp_owner,
//                                                  jl_binding_t *bnd);
extern jl_method_t *jl_method_def(jl_svec_t *argdata, jl_methtable_t *mt, jl_code_info_t *f, jl_module_t *module);
extern size_t jl_get_world_counter(void) ;
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
extern jl_value_t *jl_box_uint8pointer(uint8_t *x);
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
extern uint8_t *jl_unbox_uint8pointer(jl_value_t *v) ;

extern int jl_get_size(jl_value_t *val, size_t *pnt);


// custom helpers
extern char *jl_unbox_string(jl_value_t *v) ;
extern void jl_show(jl_value_t *v) ;
