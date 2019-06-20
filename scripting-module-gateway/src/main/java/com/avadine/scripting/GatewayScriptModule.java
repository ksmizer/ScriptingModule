package com.avadine.scripting;
import org.python.util.PythonInterpreter;

public class GatewayScriptModule extends AbstractScriptModule {
    
    @Override
    protected void runImpl(String pythonScript) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec(pythonScript);
    }

}
