 #include "julia.h"

 char * jl_unbox_charpointer(jl_value_t *v) {
     return (char *)jl_unbox_voidpointer(jl_value_t *v);
 }
