package simpl.interpreter;

import java.io.FileInputStream;
import java.io.InputStream;

import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Type;
import simpl.typing.TypeError;

public class Interpreter {

    public void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;
            Type t_tmp = program.typecheck(new DefaultTypeEnv()).t;
            Value v_tmp = program.eval(new InitialState());
            if(v_tmp instanceof ConsValue)
            {   
                System.out.println("list@"+((ConsValue)v_tmp).Length());
            }
            else if(v_tmp instanceof RefValue)
            {
                System.out.println(v_tmp);
            }
            else if(v_tmp instanceof PairValue)
            { 
                System.out.println(v_tmp);  
            }
            else{
                System.out.println(program.eval(new InitialState()));
            }
            
        }
        catch (SyntaxError e) {
            System.out.println("syntax error");
        }
        catch (TypeError e) {
            System.out.println("type error");
        }
        catch (RuntimeError e) {
            System.out.println("runtime error");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename);
    }

    public static void main(String[] args) {
        interpret("./"+args[0]);
    }
}
