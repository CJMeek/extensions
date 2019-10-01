package dev.galasa.devtools.karaf.terminal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;

@Command(scope = "3270", name = "tab", description = "Press tab on the 3270 Terminal")
@Service
public class TerminalTab implements Action {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public Object execute() throws Exception {

        final TerminalHolder terminalHolder = TerminalHolder.getHolder();
        if (terminalHolder.terminal == null) {
            this.logger.error("The Terminal is not connected, use 3270:connect");
            return null;
        }

        try {
            terminalHolder.terminal.tab();
            terminalHolder.terminal.reportScreenWithCursor();
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

        return null;
    }
}
