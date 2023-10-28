(* Common inteface-ish class for defining a common toString() method. *)
class Stringish inherits IO {
    toString(): String {{
        out_string("NotImplementedError: Class ".concat(self.type_name()).concat(" does not implement toString()."));
        abort();
        "";
    }};

    (* Returns the value of toString() as a Str object, or 'self' if this object is a Str. *)
    str(): Str {
        case self of
            x      : Str    => x;
            unused : Object => (new Str).from(toString());
        esac
    };
};

(* Better String class, offering more and useful methods. *)
class Str inherits Stringish {
    value: String;

    (* Initializes this Str with the given String value. *)
    from(value_: String): SELF_TYPE {{
        value <- value_;
        self;
    }};

    (* Initializes this Str from the given Int, using base-10. *)
    fromInt(int: Int): SELF_TYPE {{
        if int < 0 then {
            fromInt(0 - int);
            value <- "-".concat(value);
        } else if   int < 10 then (
            if      int = 0 then value <- "0"
            else if int = 1 then value <- "1"
            else if int = 2 then value <- "2"
            else if int = 3 then value <- "3"
            else if int = 4 then value <- "4"
            else if int = 5 then value <- "5"
            else if int = 6 then value <- "6"
            else if int = 7 then value <- "7"
            else if int = 8 then value <- "8"
            else if int = 9 then value <- "9"
            else { out_string("Impossible\n"); abort(); ""; }
            fi fi fi fi fi fi fi fi fi fi
        ) else
            -- Convert last (least significant) digit first
            let rest: Int <- int / 10 in let lastDigit: String <- fromInt(int - rest * 10).toString() in {
                -- Then, convert the rest
                fromInt(rest);
                -- Concat last digit to the result
                value <- value.concat(lastDigit);
            }
        fi fi;
        self;
    }};

    (* Returns the length of this Str. *)
    length(): Int { value.length() };

    (* Concatenates this Str with another. *)
    concat(other: Str): Str { (new Str).from(value.concat(other.toString())) };

    (* Returns a portion of string string starting at the given index, of the given length. *)
    substr(index: Int, length: Int): Str { (new Str).from(value.substr(index, length)) };

    (* Check equality with another Str. *)
    equals(other: Str): Bool { value = other.toString() };

    (* Returns a List<Str> containing the result of splitting this string using the given delimiter.
    If the delimiter is found at the beginning/end of the string or is found consecutively inside the string,
    there will be empty strings in the output list.

    Examples:
    "a|b|c".split("|") = [a,b,c]
    "hello world!".split("l") = [he,,o wor,d!]
    "abc//def////xyz".split("//") = [abc,def,,xyz]
    *)
    split(delim: String): List { (new List).from(splitBase(delim)) };

    splitBase(delim: String): ListBase {
        let index: Int <- indexOf(delim, 0) in
            if index = 0-1 then (new EmptyList).cons(self)
            else substr(index + delim.length(), length() - index - delim.length()).splitBase(delim).cons(substr(0, index)) fi
    };

    (* Returns the index of the first occurence of the given substring, or -1 if the substring is not found. *)
    indexOf(substr: String, startAt: Int): Int {
        if value.length() <= substr.length() then
            -- If they are equal, index is one. If substr is longer than value it cannot be found inside it.
            if value = substr then 0 else 0-1 fi
        else
            let i: Int <- 0, max: Int <- value.length() - substr.length(), result: Int <- 0-1 in {
                -- Loop through all possible start indices of substr within value
                while i <= max loop
                    -- Test if it is found at this index
                    if value.substr(i, substr.length()) = substr then {
                        -- Save the result and break
                        result <- i;
                        i <- max + 1;
                    } else
                        -- Not found here, continue
                        i <- i + 1
                    fi
                pool;
                result;
            }
        fi
    };

    (* Returns the encapsulated String value. *)
    toString(): String { value };

    (* Converts this Str to an Int. This Str is considered to be a base-10 string representation of an integer. *)
    toInt(): Int {
        if length() = 1 then (
            if      value = "0" then 0
            else if value = "1" then 1
            else if value = "2" then 2
            else if value = "3" then 3
            else if value = "4" then 4
            else if value = "5" then 5
            else if value = "6" then 6
            else if value = "7" then 7
            else if value = "8" then 8
            else if value = "9" then 9
            else { out_string("Invalid character found in Str while doing toInt(): '".concat(value).concat("'\n")); abort(); 0; }
            fi fi fi fi fi fi fi fi fi fi
        ) else if value.substr(0, 1) = "-" then 0 - substr(1, length() - 1).toInt()
        (* Create a new string 'rest' equal to this one minus the first character. Convert it to Int.
        Also convert the first char to a single-digit Int.
        Mesure the length of 'rest' to calculate the power of 10 we need to multiply the single-digit Int by.
        Add the 2 Ints together. *)
        else let rest: Str <- substr(1, length() - 1), pow: Int <- 1, i: Int <- 0 in {
            -- Calculate the correct power of 10
            while i < rest.length() loop {
                pow <- pow * 10;
                i <- i + 1;
            } pool;
            -- Calculate the final value
            rest.toInt() + substr(0, 1).toInt() * pow;
        } fi fi
    };
};
