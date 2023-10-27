(* Common inteface-ish class for defining a common toString() method. *)
class Stringish inherits IO {
    toString(): String {{
        out_string("NotImplementedError: Class ".concat(self.type_name()).concat(" does not implement toString()."));
        abort();
        "";
    }};
};
