JL_SHARE = $(shell julia -e 'print(joinpath(Sys.BINDIR, Base.DATAROOTDIR, "julia"))')
CFLAGS   += $(shell $(JL_SHARE)/julia-config.jl --cflags)
CXXFLAGS += $(shell $(JL_SHARE)/julia-config.jl --cflags)
LDFLAGS  += $(shell $(JL_SHARE)/julia-config.jl --ldflags)
LDLIBS   += $(shell $(JL_SHARE)/julia-config.jl --ldlibs)


CFLAGS   += -I$(JAVA_HOME)/include/ -I$(JAVA_HOME)/include/darwin

all: libjulia4j


CC       = gcc
CFLAGS  += -I. -FPIC
LDFLAGS += -shared

SRC    = julia4j_wrap.c
OBJ    = $(SRC:.c=.o)

%.o: %.c
	$(CC) -c -o $@ $< $(CFLAGS)

libjulia4j: $(OBJ)
	$(CC) -o $@.dylib $^ $(LDLIBS) $(LDFLAGS)
